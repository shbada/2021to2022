package hello.springtx.apply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 스프링의 트랜잭션 AOP 기능은 public 메서드에만 트랜잭션을 적용하도록 기본 설정이 되어있다.
 그래서 protected , private , package-visible 에는 트랜잭션이 적용되지 않는다.

 @Transactional
 public class Hello {
     public method1();
     method2():
     protected method3();
     private method4();
 }

 이렇게 클래스 레벨에 트랜잭션을 적용하면 모든 메서드에 트랜잭션이 걸릴 수 있다.
 그러면 트랜잭션을 의도하지 않는 곳 까지 트랜잭션이 과도하게 적용된다.
 트랜잭션은 주로 비즈니스 로직의 시작점에 걸기때문에 대부분 외부에 열어준 곳을 시작점으로 사용한다.
 이런 이유로 public 메서드에만 트랜잭션을 적용하도록 설정되어 있다.

 @Transactional
 public void internal() { // public 이 아니면 트랜잭션이 걸리지않음
     log.info("call internal");
     printTxInfo();
 }
 */
@Slf4j
@SpringBootTest
public class InternalCallV2Test {
    @Autowired
    CallService callService; // 메서드에 @Transactional 있으므로 프록시 객체가 주입되겠다.

    @Test
    void printProxy() {
        // callService class=class hello.springtx.apply.InternalCallV1Test$CallService$$EnhancerBySpringCGLIB$$4b087fca
        log.info("callService class={}", callService.getClass());
    }

//    @Test
//    void internalCall() {
//        callService.internal(); // @Transactional
//    }

    @Test
    void externalCallV2() {
        /*
            call external
            tx active=false
            tx readOnly=false
            Getting transaction for [hello.springtx.apply.InternalCallV2Test$InternalCallService.internal]
            call internal
            tx active=true
            tx readOnly=false

            실제 호출되는 흐름
            1. 클라이언트인 테스트 코드는 callService.external() 을 호출한다.
            2. callService 는 실제 callService 객체 인스턴스이다.
            3. callService 는 주입 받은 internalService.internal() 을 호출한다.
            4. internalService 는 트랜잭션 프록시이다. internal() 메서드에 @Transactional 이 붙어
            있으므로 트랜잭션 프록시는 트랜잭션을 적용한다.
            5. 트랜잭션 적용 후 실제 internalService 객체 인스턴스의 internal() 을 호출한다.
         */
        callService.external();
    }

    @TestConfiguration
    static class InternalCallV1TestConfig {
        @Bean
        CallService callService() {
            return new CallService(internalCallService());
        }

        @Bean
        InternalCallService internalCallService() {
            return new InternalCallService();
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class CallService {
        private final InternalCallService internalCallService;

        public void external() {
            // 직접 트랜잭션 코드를 명시해주거나, 그런 방법은 조금 복잡하다.

            log.info("call external");
            printTxInfo();

            // this : 나 자신의 인스턴스
            // this.internal() 은 나 자신의 인스턴스의 메서드를 호출한다. -> 프록시 객체를 호출하지 않는다.
//            this.internal(); // 트랜잭션 있는 메서드 수행

            internalCallService.internal();
        }

        @Transactional
        public void internal() {
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

    @Slf4j
    static class InternalCallService {
        @Transactional
        public void internal() {
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
