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
}
