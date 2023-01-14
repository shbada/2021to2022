package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import
        org.springframework.transaction.support.TransactionSynchronizationManager;
import javax.annotation.PostConstruct;
@SpringBootTest
public class InitTxTest {
    @Autowired
    Hello hello;

    @Test
    void go() {
        // 초기화 코드는 스프링이 초기화 시점에 호출한다.
    }

    @TestConfiguration
    static class InitTxTestConfig {
        @Bean
        Hello hello() {
            return new Hello();
        }
    }

    @Slf4j
    static class Hello {
        /**
         * 초기화 코드(예: @PostConstruct )와 @Transactional 을 함께 사용하면 트랜잭션이 적용되지 않는다.
         * 왜냐하면 초기화 코드가 먼저 호출되고, 그 다음에 트랜잭션 AOP가 적용되기 때문이다.
         * 따라서 초기화 시점에는 해당 메서드에서 트랜잭션을 획득할 수 없다.
         */
        @PostConstruct
        @Transactional
        public void initV1() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();

            // Hello init @PostConstruct tx active = false
            log.info("Hello init @PostConstruct tx active = {}", isActive);
        }

        /**
         * 이 이벤트는 트랜잭션 AOP를 포함한 스프링이 컨테이너가 완전히 생성되고 난 다음에 이벤트가 붙은 메서드를 호출해준다.
         * 따라서 init2() 는 트랜잭션이 적용된 것을 확인할 수 있다.
         */
        @EventListener(value = ApplicationReadyEvent.class)
        @Transactional
        public void init2() {
            boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();

            // Hello init ApplicationReadyEvent tx active = true
            log.info("Hello init ApplicationReadyEvent tx active = {}", isActive);
        }
    }
}
