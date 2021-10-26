package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 앱 전체를 설정하고 구성한다는 의미
 * 실제 동작에 필요한 구현 객체를 생성하는 클래스
 * (생성자를 통해서 주입(연결) 해준다)
 *
 */
public class AppConfig {
    public MemberService memberService() {
        // new MemoryMemberRepository() 생성하고 파라미터로 new MemberServiceImpl 한 결과를 전달한다.
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
