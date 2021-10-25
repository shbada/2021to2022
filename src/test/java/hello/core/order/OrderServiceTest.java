package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        /* 1) Member 등록 */
        Long memberId = 1L; // long type 선언시 null 불가능 (Wrapper 타입 사용해서 NULL 도 가능하도록 함)

        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        /* 2) Order 등록 */
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }
}