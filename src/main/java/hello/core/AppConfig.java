package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
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
        // new MemoryMemberRepository() 중복 코드
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 중복코드 리팩터링
     * 역할이 한눈에 보인다. 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악이 가능하다.
     * @return
     */
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
