package hello.springtx.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
public class TxApplyBasicTest {
    @Autowired
    BasicService basicService;

    @Test
    void proxyCheck() {
        // BasicService$$EnhancerBySpringCGLIB...
        log.info("aop class={}", basicService.getClass());

        // aop class=class hello.springtx.apply.TxApplyBasicTest$BasicService$$EnhancerBySpringCGLIB$$90fb8665
        assertThat(AopUtils.isAopProxy(basicService)).isTrue();
    }

    @Test
    void txTest() {
        basicService.tx(); // tx active=true
        basicService.nonTx(); // tx active=false
    }

    @TestConfiguration
    static class TxApplyBasicConfig {
        // 빈 등록
        @Bean
        BasicService basicService() {
            return new BasicService();
        }
    }

    @Slf4j
    static class BasicService {
        @Transactional
        public void tx() {
            log.info("call tx");

            // 현재 쓰레드에 트랜잭션이 적용되어 있는지 확인할 수 있는 기능
            boolean txActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }

        public void nonTx() {
            log.info("call nonTx");
            boolean txActive =
                    TransactionSynchronizationManager.isActualTransactionActive();
            log.info("tx active={}", txActive);
        }
    }
}
