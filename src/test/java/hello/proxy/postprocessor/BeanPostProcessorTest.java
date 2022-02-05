package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {
    @Test
    void basicConfig() {
        // config bean 등록
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // A는 빈으로 등록된다.
//        A a = applicationContext.getBean("beanA", A.class);
//        a.helloA();

        // bean change (A->B)
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        // A->B change : A.class not found
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean(A.class));
    }

    @Slf4j
    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA") // bean 이름
        public A a() {
            return new A();
        }

        /**
         * 빈으로 등록하기만 하면 스프링 컨테이너가 빈 후처리기로 인식하고 동작한다.
         * @return
         */
        @Bean
        public AtoBPostProcessor helloPostProcessor() {
            return new AtoBPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }

    @Slf4j
    static class AtoBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={} bean={}", beanName, bean);

            if (bean instanceof A) { // A 이면 bean change ( A -> B )
                return new B();
            }

            return bean;
        }
    }
}
