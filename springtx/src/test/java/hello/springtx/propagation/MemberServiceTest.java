package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LogRepository logRepository;

    /**
     * MemberService @Transactional:OFF
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON
     *
     * 참고) JPA를 통한 모든 데이터 변경(등록, 수정, 삭제)에는 트랜잭션이 필요하다. (조회는 트랜잭션 없이 가능하다.)
     */
    @Test
    void outerTxOff_success() {
        // given
        String username = "outerTxOff_success";

        // when
        memberService.joinV1(username);

        // then: 모든 데이터가 정상 저장된다.
        // MemberRepository 와 관련된 모든 데이터는 정상 커밋되고, 트랜잭션B는 완전히 종료된다.
        // 이후에 LogRepository 를 통해 트랜잭션C를 시작하고, 정상 커밋한다.
        assertTrue(memberRepository.find(username).isPresent()); // 트랜잭션 B
        assertTrue(logRepository.find(username).isPresent()); // 트랜잭션 C
    }

    /**
     * MemberService @Transactional:OFF
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    void outerTxOff_fail() {
        // given
        String username = "로그예외_outerTxOff_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        // then: 완전히 롤백되지 않고, member 데이터가 남아서 저장된다.
        assertTrue(memberRepository.find(username).isPresent()); // 커밋
        assertTrue(logRepository.find(username).isEmpty()); // 롤백
    }

    /**
     * 트랜잭션 하나만 사용하기
     * 회원 리포지토리와 로그 리포지토리를 하나의 트랜잭션으로 묶는 가장 간단한 방법은 이 둘을 호출하는 회원 서비스에만 트랜잭션을 사용하는 것이다.
     *
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:OFF
     * LogRepository @Transactional:OFF
     */
    @Test
    void singleTx() {
        // given
        String username = "singleTx";

        // when
        memberService.joinV1(username);

        // then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON
     */
    @Test
    void outerTxOn_success() {
        // given
        String username = "outerTxOn_success";

        // when
        memberService.joinV1(username);

        // then: 모든 데이터가 정상 저장된다.
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isPresent());

        // MemberService 호출하면서 트랜잭션 AOP 호출 : 신규 트랜잭션 생성, 물리 트랜잭션 시작
        // MemberRepository 호출하면서 트랜잭션 AOP 호출 : 이미 트랜잭션 존재하여 기존 트랜잭션에 참여
        // MemberRepository : 신규 트랜잭션이 아니므로 실제 커밋 수행하지 않음
        // LogRepository : 이미 트랜잭션 존재하여 기존 트랜잭션에 참여
        // LogRepository : 신규 트랜잭션이 아니므로 실제 커밋 수행하지 않음
        // MemberService : 로직 호출이 끝나고, 신규 트랜잭션이므로 물리 커밋을 호출함
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    void outerTxOn_fail() {
        // given
        String username = "로그예외_outerTxOn_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV1(username))
                .isInstanceOf(RuntimeException.class);

        // then: 모든 데이터가 롤백된다.
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());

        // MemberService 호출하면서 트랜잭션 AOP 호출된다. : 신규 트랜잭션 생성, 물리 트랜잭션 시작
        // MemberRepository : 기존 트랜잭션에 참여
        // MemberRepository : 실제 커밋 호출하지 않는다.
        // LogRepository : 기존 트랜잭션에 참여
        // LogREepository : 예외 발생 --> 트랜잭션 AOP가 해당 예외를 받게된다.
        // -> 트랜잭션 AOP는 런타임 예외가 발생했으므로 트랜잭션 매니저에 롤백을 요청한다.
        // -> 이 경우 신규 트랜잭션이 아니므로 물리 롤백을 호출하지는 않는다. 대신에 rollbackOnly 를 설정한다.
        // -> LogRepository 가 예외를 던졌기 때문에 트랜잭션 AOP도 해당 예외를 그대로 밖으로 던진다.
        // MemberService 에서도 런타임 예외를 받게 되는데, 여기 로직에서는 해당 런타임 예외를 처리하지 않고 밖으로 던진다.
        // -> 트랜잭션 AOP는 런타임 예외가 발생했으므로 트랜잭션 매니저에 롤백을 요청한다.
        // -> 이 경우 신규 트랜잭션이므로 물리 롤백을 호출한다.
        // -> 참고로 이 경우 어차피 롤백이 되었기 때문에, rollbackOnly 설정은 참고하지 않는다.
        // -> MemberService 가 예외를 던졌기 때문에 트랜잭션 AOP도 해당 예외를 그대로 밖으로 던진다.
        // 클라이언트A는 LogRepository 부터 넘어온 런타임 예외를 받게 된다.
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional:ON Exception
     */
    @Test
    void recoverException_fail() {
        // given
        String username = "로그예외_recoverException_fail";

        // when
        assertThatThrownBy(() -> memberService.joinV2(username))
                .isInstanceOf(UnexpectedRollbackException.class);

        // then: 모든 데이터가 롤백된다.
        /*
            내부 트랜잭션에서 rollbackOnly 를 설정하기 때문에 결과적으로 정상 흐름 처리를 해서
            외부 트랜잭션에서 커밋을 호출해도 물리 트랜잭션은 롤백된다.
            그리고 UnexpectedRollbackException 이 던져진다.
         */
        assertTrue(memberRepository.find(username).isEmpty());
        assertTrue(logRepository.find(username).isEmpty());
        /*
            LogRepository 에서 예외가 발생한다. 예외를 던지면 LogRepository 의 트랜잭션 AOP가 해당 예외를 받는다.
            신규 트랜잭션이 아니므로 물리 트랜잭션을 롤백하지는 않고, 트랜잭션 동기화 매니저에 rollbackOnly 를 표시한다.
            이후 트랜잭션 AOP는 전달 받은 예외를 밖으로 던진다.
            예외가 MemberService 에 던져지고, MemberService 는 해당 예외를 복구한다. 그리고 정상적으로 리턴한다.
            정상 흐름이 되었으므로 MemberService 의 트랜잭션 AOP는 커밋을 호출한다.
            커밋을 호출할 때 신규 트랜잭션이므로 실제 물리 트랜잭션을 커밋해야 한다. 이때 rollbackOnly 를 체크한다.
            rollbackOnly 가 체크 되어 있으므로 물리 트랜잭션을 롤백한다.
            트랜잭션 매니저는 UnexpectedRollbackException 예외를 던진다.
            트랜잭션 AOP도 전달받은 UnexpectedRollbackException 을 클라이언트에 던진다.
         */
    }

    /**
     * MemberService @Transactional:ON
     * MemberRepository @Transactional:ON
     * LogRepository @Transactional(REQUIRES_NEW) Exception
     */
    @Test
    void recoverException_success() {
        // given
        String username = "로그예외_recoverException_success";

        // when
        memberService.joinV2(username);

        // then: member 저장, log 롤백
        assertTrue(memberRepository.find(username).isPresent());
        assertTrue(logRepository.find(username).isEmpty());

        /*
            LogRepository 에서 예외가 발생한다. 예외를 던지면 LogRepository 의 트랜잭션 AOP가 해당 예외를 받는다.
            REQUIRES_NEW 를 사용한 신규 트랜잭션이므로 물리 트랜잭션을 롤백한다.
            물리 트랜잭션을 롤백했으므로 rollbackOnly 를 표시하지 않는다.
            여기서 REQUIRES_NEW 를 사용한 물리 트랜잭션은 롤백되고 완전히 끝이 나버린다.
            이후 트랜잭션 AOP는 전달 받은 예외를 밖으로 던진다.
            예외가 MemberService 에 던져지고, MemberService 는 해당 예외를 복구한다. 그리고 정상적으로 리턴한다.
            정상 흐름이 되었으므로 MemberService 의 트랜잭션 AOP는 커밋을 호출한다.
            커밋을 호출할 때 신규 트랜잭션이므로 실제 물리 트랜잭션을 커밋해야 한다. 이때 rollbackOnly 를 체크한다.
            rollbackOnly 가 없으므로 물리 트랜잭션을 커밋한다.
            이후 정상 흐름이 반환된다.
         */


        /*
            REQUIRES_NEW 를 사용하면 하나의 HTTP 요청에 동시에 2개의 데이터베이스 커넥션을 사용하게 된다.
            따라서 성능이 중요한 곳에서는 이런 부분을 주의해서 사용해야 한다.
         */
    }
}
