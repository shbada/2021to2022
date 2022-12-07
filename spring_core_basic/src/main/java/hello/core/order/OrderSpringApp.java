package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderSpringApp {
    public static void main(String[] args) {
        /* AppConfig 에서 가져온 객체로 대체한다 */
        // 스프링이 AppConfig 에 있는 환경 설정을 가지고 다 등록하고 관리해준다.
        // ApplicationContext 을 '스프링 컨테이너'라고 한다. (ApplicationContext: 인터페이스)
        /**
         * 스프링 컨테이너를 통해서 필요한 스프링 빈 객체를 찾아야한다. (등록/관리는 스프링이 해줌)
         * 기존에는 개발자가 직접 자바코드로 모든것을 했다면 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경됨
         */
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);

        // Bean 이름이 orderService 이고, 타입이 OrderService 인걸 찾는다.
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

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
