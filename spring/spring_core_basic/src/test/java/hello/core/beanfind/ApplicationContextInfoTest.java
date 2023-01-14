package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 빈 이름을 배열로 받는다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        /*
            name = appConfig object = hello.core.AppConfig$$EnhancerBySpringCGLIB$$f8a4bbb6@732f29af
            name = memberService object = hello.core.member.MemberServiceImpl@d3957fe
            name = orderService object = hello.core.order.OrderServiceImpl@6622fc65
            name = memberRepository object = hello.core.member.MemoryMemberRepository@299321e2
            name = discountPolicy object = hello.core.discount.RateDiscountPolicy@23fb172e
         */
        Arrays.stream(beanDefinitionNames).forEach(beanDefinitionName -> {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        });
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        // 빈 이름을 배열로 받는다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        /*
            name = appConfig object = hello.core.AppConfig$$EnhancerBySpringCGLIB$$f8a4bbb6@732f29af
            name = memberService object = hello.core.member.MemberServiceImpl@d3957fe
            name = orderService object = hello.core.order.OrderServiceImpl@6622fc65
            name = memberRepository object = hello.core.member.MemoryMemberRepository@299321e2
            name = discountPolicy object = hello.core.discount.RateDiscountPolicy@23fb172e
         */
        // bean 하나에 대한 메타데이터 정보
        // spring 이 내부 구현을 위해 등록한 빈이 아닌 내가 등록한 빈 을 출력
        // spring 내부에서 등록된 빈 출력
        Arrays.stream(beanDefinitionNames).forEach(beanDefinitionName -> {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }

            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        });
    }
}
