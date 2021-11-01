package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 앱 전체를 설정하고 구성한다는 의미
 * 실제 동작에 필요한 구현 객체를 생성하는 클래스
 * (생성자를 통해서 주입(연결) 해준다)
 *
 */
@Configuration /* 다음단계) Spring annotation 적용 */
public class AppConfig {
    /**
     * 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
     * 무상태로 설계해야한다. (stateless)
     */


    @Bean /* 빈 등록 (스프링 컨테이너에 등록됨) : 메서드 명으로 등록, 빈 이름은 무조건 중복되면 안됨. */
    public MemberService memberService() {
        // new MemoryMemberRepository() 생성하고 파라미터로 new MemberServiceImpl 한 결과를 전달한다.
        // new MemoryMemberRepository() 중복 코드
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 중복코드 리팩터링
     * 역할이 한눈에 보인다. 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악이 가능하다.
     * @return
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * 할인 정책 변경 FixDiscountPolicy -> RateDiscountPolicy (AppConfig.java 만 변경 발생)
     * 구성 영역은 당연히 변경된다. 구성 역할을 담당하는 AppConfig.java 가 변경 발생한다.
     * OrderServiceImpl, MemberServiceImpl 의 수정은 없다 (DIP, OCP:확장엔 열려있고, 수정엔 닫혀있다.; 만족)
     * @return
     */
    @Bean
    public DiscountPolicy discountPolicy() {
        /* 변경요건) RateDiscountPolicy() 로 변경 */
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy(); //  이 코드만 변경하면 적용된다.
    }
}
