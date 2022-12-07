package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        // BeanB beanB = ac.getBean("beanB", BeanB.class); // exclude 되어있으므로 컴포넌트 스캔 대상에서 빠졌다.
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            // type = FilterType.ANNOTATION 기본값이라 생략 가능
            // ASSIGNABLE_TYPE (타입과 자식 타입을 인식해서 동작), ASPECTJ(AspectJ 패턴 사용), REGEX(정규표현식), CUSTOM(TypeFilter 만들어서 처리)
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )

    static class ComponentFilterAppConfig {

    }
}
