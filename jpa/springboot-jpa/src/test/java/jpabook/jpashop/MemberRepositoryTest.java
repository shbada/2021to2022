package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberTestRepository memberTestRepository;

    @Test
    @Transactional // 스프링 테스트 실행 후 DB를 롤백한다.
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setName("memberA");
        Long savedId = memberTestRepository.save(member);

        // when
        /* select 쿼리는 실행되지 않았다. 위에서 insert 하고 1차 캐시에 데이터가 있기 때문이다. */
        Member findMember = memberTestRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        /* True > findMember 와 member 은 같다 > 같은 트랜잭션 안에서 같은 영속성을 가지기 때문에 같은 ID를 가져서 같은 엔티티로 본다. (1차 캐시) */
        Assertions.assertThat(findMember).isEqualTo(member);

    }
}