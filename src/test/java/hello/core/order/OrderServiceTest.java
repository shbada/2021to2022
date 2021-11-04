package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
//    MemberService memberService = new MemberServiceImpl();
//    OrderService orderService = new OrderServiceImpl();

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        // AppConfig 로 DI
        AppConfig appConfig = new AppConfig();

        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

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

    @Test
    void fieldInjectionTEst() {
        // 필드 인젝션은 set 메서드가 별도로 필요하다. (테스트시)
        // 따로 주입할 방법이 없다.
        // orderService.setMemberRepository(new MemoryMemberRepository());
    }
}