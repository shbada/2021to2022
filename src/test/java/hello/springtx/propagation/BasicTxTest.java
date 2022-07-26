package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import javax.sql.DataSource;

@Slf4j
@SpringBootTest
public class BasicTxTest {
    @Autowired
    PlatformTransactionManager txManager;

    @TestConfiguration
    static class Config {
        // 스프링이 DataSource 를 주입해준다. (HikariProxyConnection)
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

    @Test
    void commit() {
        log.info("트랜잭션 시작");

        /* 트랜잭션 매니저를 통해 트랜잭션을 시작(획득)한다. */
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 커밋 시작");
        txManager.commit(status); // Committing JDBC transaction -> Releasing JDBC Connection

        log.info("트랜잭션 커밋 완료");
    }

    @Test
    void rollback() {
        log.info("트랜잭션 시작");
        TransactionStatus status = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션 롤백 시작");
        txManager.rollback(status); // Rolling back JDBC transaction on Connection -> Releasing JDBC Connection

        log.info("트랜잭션 롤백 완료");
    }

    @Test
    void double_commit() {
        log.info("트랜잭션1 시작");
        // Acquired Connection [HikariProxyConnection@411756754 wrapping conn0:
        // -> 트랜잭션1을 시작하고, 커넥션 풀에서 conn0 커넥션을 획득했다.
        TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션1 커밋");

        // Releasing JDBC Connection
        // -> 트랜잭션1을 커밋하고, 커넥션 풀에 conn0 커넥션을 반납했다.
        // 트랜잭션1은 conn0 커넥션을 모두 사용하고 커넥션 풀에 반납까지 완료
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        // Acquired Connection [HikariProxyConnection@1461464792 wrapping conn0:
        // -> 트랜잭션2을 시작하고, 커넥션 풀에서 conn0 커넥션을 획득했다.
        // 트랜잭션2가 conn0 를 커넥션 풀에서 획득한 것이다.
        TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션2 커밋");

        // Releasing JDBC Connection
        // -> 트랜잭션2을 커밋하고, 커넥션 풀에 conn0 커넥션을 반납했다.
        txManager.commit(tx2);

        // Acquired Connection [HikariProxyConnection@411756754 wrapping conn0:
        // Acquired Connection [HikariProxyConnection@1461464792 wrapping conn0:
        // 물리 커넥션 conn0은 같지만, 주소는 다르다.
        // conn0 을 통해 커넥션이 재사용 된 것을 확인할 수 있고,
        // HikariProxyConnection@411756754 , HikariProxyConnection@1461464792을 통해 각각 커넥션 풀에서 커넥션을 조회한 것을 확인할 수 있다.
    }

    @Test
    void double_commit_rollback() {
        log.info("트랜잭션1 시작");
        // Acquired Connection [HikariProxyConnection@411756754 wrapping conn0
        TransactionStatus tx1 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션1 커밋");
        txManager.commit(tx1);

        log.info("트랜잭션2 시작");
        // Acquired Connection [HikariProxyConnection@1461464792 wrapping conn0
        TransactionStatus tx2 = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("트랜잭션2 롤백");
        txManager.rollback(tx2);
    }
}
