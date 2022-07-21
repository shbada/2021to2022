package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;


/**
 1. 클래스의 메서드 (우선순위가 가장 높다.)
 2. 클래스의 타입
 3. 인터페이스의 메서드 : 인터페이스에 @Transactional 사용하는 것은 스프링 공식 메뉴얼에서 권장하지 않는 방법이다.
 4. 인터페이스의 타입 (우선순위가 가장 낮다.)
 */
@SpringBootTest
public class TxLevelTest {
    @Autowired
    LevelService service;

    @Test
    void orderTest() {
        service.write();
        service.read();
    }

    @TestConfiguration
    static class TxApplyLevelConfig {
        @Bean
        LevelService levelService() {
            return new LevelService();
        }
    }

    @Slf4j
    @Transactional(readOnly = true)
    static class LevelService {
        @Transactional(readOnly = false) // false : default (@Transactional == @Transactional(readOnly=false))
        public void write() {
            log.info("call write");

            // tx active=true
            // tx readOnly=false
            printTxInfo();
        }

        public void read() {
            log.info("call read");

            // tx active=true
            // tx readOnly=true
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