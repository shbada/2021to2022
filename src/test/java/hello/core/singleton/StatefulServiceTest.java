package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        // ThreadB : B 사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        // threadA : 사용자 A주문 금액 조회
        int priceA = statefulService1.getPrice();
        int priceB = statefulService2.getPrice();

        /**
         * 공유필드는 꼭 조심해야한다.
         * 스프링 빈은 항상 무상태(stateless)로 설계하자.
         */
        // 중간에 ThreadB 가 변경해버렸다.
        // Assertions.assertThat(priceA).isEqualTo(10000); // 10000원이 아닌 20000가 나온다.

        Assertions.assertThat(priceA).isEqualTo(20000);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}