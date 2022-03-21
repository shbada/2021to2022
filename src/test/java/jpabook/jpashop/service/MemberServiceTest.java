package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) // Junit 실행할때 스프링이랑 엮어서 실행하겠다 라는 의미 (junit5~ 부터는 생략 가능할수도)
@SpringBootTest // 스프링부트를 띄운 상태로 테스트 하겠다
@Transactional // 트랜잭션
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    // @Rollback(false) // rollback 안하고 commit 수행하여 쿼리가 찍힌다.
    void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        /**
         * insert 쿼리가 없다.
         * persist() 했다고 해서, insert 쿼리가 바로 나가는 것은 아니다.
         * 트랜잭션이 커밋 될때 insert 쿼리가 나간다 (JPA의 동작방식, 따라서 트랜잭션이 중요하다)
         * 테스트일때 insert 커밋 전에 롤백해버린다. 따라서 쿼리 실행을 보려면 @Rollback(false)를 선언해라.
         * 롤백을 할텐데 insert 쿼리를 날릴 필요가 없기 때문이다.
         *
         * @Autowired EntityManager em; 선언 후 em.flush(); 사용시 커밋 할 수 있다.
         */
        // when (이런 경우에)
        Long savedId = memberService.join(member);

        // then (이렇게 되어야한다)
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        // memberService.join(member2); // 예외가 발생해야한다. (같은 이름으로 두번째 join 이기 때문에)
        // Exception 이 터졌을때 아래 코드를 타지 않아야 했으므로.

        try {
            memberService.join(member2);
        } catch (IllegalStateException e) {
            return; // 에러가 발생하면 pass 여야 하므로.
        }

        // then
        fail("예외가 발생해야 한다."); // 코드가 실행되다가 여길 오면 안된다. 여길 타면 fail 이라는 의미이다.
    }


    // Junit4 @Test(expected = IllegalStateException.class)
    void 중복_회원_예외_개선코드() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야한다. (같은 이름으로 두번째 join 이기 때문에)
        // Exception 이 터졌을때 아래 코드를 타지 않아야 했으므로.

        /* 위 어노테이션 설정으로 try~catch 생략 가능 */

        // then
        fail("예외가 발생해야 한다."); // 코드가 실행되다가 여길 오면 안된다. 여길 타면 fail 이라는 의미이다.
    }
}