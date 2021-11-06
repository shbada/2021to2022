package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class BeanDefinitionTest {
    /**
     * xml -> java code
     * 1) 직접 생성 가능
     * 2) 팩토리 메서드 사용 가능 (AppConfig.java : 팩토리 메서드를 통해 등록하는 방법이라고 한다.)
     * factoryBeanName=appConfig; factoryMethodName=memberRepository;
     */
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        Arrays.stream(beanDefinitionNames).forEach(beanDefinitionName -> {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName
                        + " beanDefinition = " + beanDefinition);
            }
        });
    }

    /**
     * 편리한 자동 기능을 기본으로 사용하자. (@Component, @Controller, @Service, @Repository 등)
     * 수동 빈 설정은 언제?
     * 업무 로직(비즈니스 로직 요구사항을 개발할때 추가되거나 변경된다.), 기술 지원 빈 (공통 관심사(AOP) 처리, 데이터베이스 연결, 공통 로그 처리 등과 같이
     * 업무 로직을 지원하기 위한 하부 기술이나 공통 기술들) 이라고 하는데,
     * 여기서 업무로직은 숫자도 매우 많고 유사한 패턴이 있어서 자동 기능을 사용해야하고,
     * 기술 지원 로직은 그 수가 매우 적고, 보통 애플리케이션 전반에 걸쳐서 광범위하게 영향을 미치므로 가급적 수동 빈 등록을 사용하는게 좋다.
     *
     * 애플리케이션에 광범위하게 영향을 미치는 기술 지원 객체는 수동 빈으로 등록해서 딱! 설정 정보에 바로 나타나게 하는것이 유지보수에 좋다.
     *
     * 비즈니스 로직 중에서 다형성을 적극 활용할때는 수동 빈 관리가 더 좋을 경우도 있다. (다형성)
     * 어떤 빈들이 주입될지, 빈의 이름 등을 파악하기 쉽도록 DiscountPolicy(예시) 의 구현 빈들만 따로 모아서 특정 패키지아 모아두는게 좋다.
     *
     * 스프링/스프링부트가 자동으로 등록하는 많은 빈들은 예외다. 이는 스프링 자체를 잘 이해하고 의도대로 잘 사용해야한다.
     * 스프링 부트의 경우 DataSource 같은 데이터베이스 연결에 사용하는 기술 지원 로직까지 내부에서 자동으로 등록한다.
     *
     * 반면에, 스프링 부트가 아니라 내가 직접 기술 지원 객체를 스프링 빈으로 등록한다면 수동으로 등록해서 명확하게 들어내는 것이 좋다.
     *
     */
}
