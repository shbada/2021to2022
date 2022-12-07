package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@SpringBootTest
public class InternalCallV1Test {
    @Autowired
    CallService callService; // 메서드에 @Transactional 있으므로 프록시 객체가 주입되겠다.

    @Test
    void printProxy() {
        // callService class=class hello.springtx.apply.InternalCallV1Test$CallService$$EnhancerBySpringCGLIB$$4b087fca
        log.info("callService class={}", callService.getClass());
    }

    @Test
    void internalCall() {
        callService.internal(); // @Transactional
    }

    @Test
    void externalCall() {
        /*
            tx active=false
            tx readOnly=false
            call internal
            tx active=false
            tx readOnly=false

            internal()에는 @Transactional 이 있는데도 적용되지 않았다.
         */

        /* 실제 호출되는 흐름
            1. 클라이언트인 테스트 코드는 callService.external() 을 호출한다. 여기서 callService 는
            트랜잭션 프록시이다.
            2. callService 의 트랜잭션 프록시가 호출된다.
            3. external() 메서드에는 @Transactional 이 없다. 따라서 트랜잭션 프록시는 트랜잭션을 적용하지
            않는다.
            4. 트랜잭션 적용하지 않고, 실제 callService 객체 인스턴스의 external() 을 호출한다.
            5. external() 은 내부에서 internal() 메서드를 호출한다. 그런데 여기서 문제가 발생한다.

            -> 5번에서 Proxy 객체 호출이 아닌 실제 객체를 호출한다.
         */
        callService.external(); // 얘는 일단 @Transactional 이 없다.
    }

    @TestConfiguration
    static class InternalCallV1TestConfig {
        @Bean
        CallService callService() {
            return new CallService();
        }
    }

    @Slf4j
    static class CallService {
        public void external() {
            // 직접 트랜잭션 코드를 명시해주거나, 그런 방법은 조금 복잡하다.

            log.info("call external");
            printTxInfo();

            // this : 나 자신의 인스턴스
            // this.internal() 은 나 자신의 인스턴스의 메서드를 호출한다. -> 프록시 객체를 호출하지 않는다.
            this.internal(); // 트랜잭션 있는 메서드 수행
        }

        @Transactional
        public void internal() { // public 이 아니면 트랜잭션이 걸리지않음
            log.info("call internal");
            printTxInfo();
        }

        private void printTxInfo() {
            boolean txActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);

            boolean readOnly =
                    TransactionSynchronizationManager.isCurrentTransactionReadOnly();
            log.info("tx readOnly={}", readOnly);
        }
    }
}
