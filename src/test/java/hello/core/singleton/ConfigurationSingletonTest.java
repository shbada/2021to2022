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
}
