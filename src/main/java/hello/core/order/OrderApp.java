package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        /* AppConfig 에서 가져온 객체로 대체한다 */
        AppConfig appConfig = new AppConfig();
        OrderService orderService = appConfig.orderService();
        MemberService memberService = appConfig.memberService();

        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;

        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
        System.out.println("order = " + order);

        // order.calculatePrice = 9000
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
