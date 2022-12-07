package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        // setter 메서드 주입일때 null 에러 발생
        // creatOrder 만 하고싶다해도, createOrder 에는 필요한 빈들이 있다.
        // 이들을 만들어줘야한다.
        // 테스트 코드를 짤때는 의존관계가 눈에 잘 안보인다. 코드를 까봐야한다.
        // 만약 생성자 주입이라면? 컴파일 오류 문구로 바로 캐치할 수 있다. (바로 인지가 가능)
        // new 해서 각 객체를 넣었음. 이렇게 실행 가능.
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}