package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // test 용도로 만든 메서드 꺼내기 위해 Impl 로 꺼내기 (원래는 구현체로 안꺼냄)
        MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderServiceImpl = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberServiceImpl.getMemberRepository();
        MemberRepository memberRepository2 = orderServiceImpl.getMemberRepository();

        /*
            memberService -> memberRepository1 = hello.core.member.MemoryMemberRepository@2002348
            orderService -> memberRepository2 = hello.core.member.MemoryMemberRepository@2002348
            memberRepository = hello.core.member.MemoryMemberRepository@2002348
         */
        // 둘은 같다.
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        // 셋은 같다.
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberServiceImpl.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(memberServiceImpl.getMemberRepository()).isSameAs(orderServiceImpl.getMemberRepository());
        Assertions.assertThat(orderServiceImpl.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        // AppConfig 도 해당 호출로 빈에 등록됨
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        /**
        * class hello.core.AppConfig$$EnhancerBySpringCGLIB$$3aac460c
        * $$EnhancerBySpringCGLIB$$3aac460c 순수한 클래스라면 class hello.core.AppConfig 로 나와야한다..
        * 이것은 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은
        * 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다!
        * 임의의 다른 클래스가 바로 싱글톤이 보장되도록 해준다.
        * 예상코드) 이미 스프링 컨테이너에 등록되어있으면 컨테이너에서 찾아서 반환하고, 아니라면 새로 등록하고 등록한 객체를 반환할것임
         *
         * 부모타입을 조회하면 자식 타입도 다 끌려나오니깐.. GCLIB 도 출력된것
        */
        System.out.println("bean = " + bean.getClass());
    }
}
