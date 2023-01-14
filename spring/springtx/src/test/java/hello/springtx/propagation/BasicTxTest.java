package hello.springtx.propagation;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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

    /**
     * 외부 트랜잭션과 내부 트랜잭션이 하나의 물리 트랜잭션으로 묶이는 것
     */
    @Test
    void inner_commit() {
        log.info("외부 트랜잭션 시작");
        // 논리 트랜잭션1 -> 얘만 물리 트랜잭션 커밋을 수행할 수 있다.
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

        // 외부 트랜잭션은 처음 수행된 트랜잭션이다. 이 경우 신규 트랜잭션( isNewTransaction=true )이 된다.
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction());

        log.info("내부 트랜잭션 시작");
        // 논리 트랜잭션2
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());

        // 내부 트랜잭션은 이미 진행중인 외부 트랜잭션에 참여한다. 이 경우 신규 트랜잭션이 아니다 (isNewTransaction=false)
        // 기존 트랜잭션이 존재하므로 기존 트랜잭션에 참여한다. 기존 트랜잭션에 참여한다는 뜻은 사실 아무것도 하지 않는다는 뜻이다.
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction());

        /*
            트랜잭션을 생각해보면 하나의 커넥션에 커밋은 한번만 호출할 수 있다. 커밋이나 롤백을 하면 해당 트랜잭션은 끝나버린다.
            스프링은 어떻게 어떻게 외부 트랜잭션과 내부 트랜잭션을 묶어서 하나의 물리 트랜잭션으로 묶어서 동작하게 할까?

            내부 트랜잭션 커밋
            -> 여기서 실제로 커밋을 하지 않는다. 이미 외부 트랜잭션이 참여중이기 때문에, 이 커밋을 무시된다.
            외부 트랜잭션 커밋
            -> 여기서 실제로 커밋이 발생한다.
        */
        log.info("내부 트랜잭션 커밋");
        txManager.commit(inner); // 무시

        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer); // 커밋 수행 (처음 트랜잭션을 시작한 물리 트랜잭션만 커밋한다.)
    }

    /**
     * 외부 롤백
     */
    @Test
    void outer_rollback() {
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());

        // 내부 트랜잭션은 앞서 배운대로 직접 물리 트랜잭션에 관여하지 않는다.
        log.info("내부 트랜잭션 커밋");
        txManager.commit(inner); // 아무것도 안한다. 무시. (신규 트랜잭션이 아니기 때문에 실제 커밋을 호출하지 않는다.)

        log.info("외부 트랜잭션 롤백");
        // 결과적으로 외부 트랜잭션에서 시작한 물리 트랜잭션의 범위가 내부 트랜잭션까지 사용된다.
        // 이후 외부 트랜잭션이 롤백되면서 전체 내용은 모두 롤백된다.
        txManager.rollback(outer); // 외부 트랜잭션은 신규 트랜잭션이다. 따라서 DB 커넥션에 실제 롤백을 호출한다.
    }

    @Test
    void inner_rollback() {
        // 물리 트랜잭션을 시작한다.
        log.info("외부 트랜잭션 시작");
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());

        // 내부 트랜잭션 시작 - 기존 트랜잭션에 참여한다.
        log.info("내부 트랜잭션 시작");
        TransactionStatus inner = txManager.getTransaction(new DefaultTransactionAttribute());

        // 내부 트랜잭션을 롤백하면 실제 물리 트랜잭션은 롤백하지 않는다.
        // 대신에 기존 트랜잭션을 롤백 전용으로 표시한다. (Participating transaction failed - marking existing transaction as rollbackonly)
        // 내부 트랜잭션은 물리 트랜잭션을 롤백하지 않는 대신에 트랜잭션 동기화 매니저에 rollbackOnly=true 라는 표시를 해둔다.
        log.info("내부 트랜잭션 롤백");
        txManager.rollback(inner);

        /*
            UnexpectedRollbackException.class 이 발생
            -> 스프링은 이 경우 UnexpectedRollbackException 런타임 예외를 던진다.
               그래서 커밋을 시도했지만, 기대하지 않은 롤백이 발생했다는 것을 명확하게 알려준다.
        */
        // 커밋을 호출했지만, 전체 트랜잭션이 롤백 전용으로 표시되어 있다. 따라서 물리 트랜잭션을 롤백한다. (Global transaction is marked as rollback-only)
        log.info("외부 트랜잭션 커밋");
        assertThatThrownBy(() -> txManager.commit(outer))
                .isInstanceOf(UnexpectedRollbackException.class);
    }

    /**
     * 물리 트랜잭션을 분리하려면 내부 트랜잭션을 시작할 때 REQUIRES_NEW 옵션을 사용하면 된다.
     * 외부 트랜잭션과 내부 트랜잭션이 각각 별도의 물리 트랜잭션을 가진다.
     * 별도의 물리 트랜잭션을 가진다는 뜻은 DB 커넥션을 따로 사용한다는 뜻이다.
     * 이 경우 내부 트랜잭션이 롤백되면서 로직 2가 롤백되어도 로직 1에서 저장한 데이터에는 영향을 주지 않는다.
     */
    @Test
    void inner_rollback_requires_new() {
        log.info("외부 트랜잭션 시작");

        // 외부 트랜잭션을 시작하면서 conn0 를 획득하고 manual commit 으로 변경해서 물리 트랜잭션을 시작한다.
        TransactionStatus outer = txManager.getTransaction(new DefaultTransactionAttribute());
        log.info("outer.isNewTransaction()={}", outer.isNewTransaction()); // true

        // 내부 트랜잭션을 시작하면서 conn1 를 획득하고 manual commit 으로 변경해서 물리 트랜잭션을 시작한다.
        // 이때 con1 은 잠시 보류되고, 지금부터는 con2 가 사용된다. (내부 트랜잭션을 완료할 때 까지 con2가 사용된다.)
        log.info("내부 트랜잭션 시작");
        DefaultTransactionAttribute definition = new DefaultTransactionAttribute();
        // REQUIRES_NEW 옵션을 사용
        // 이 전파 옵션을 사용하면 내부 트랜잭션을 시작할 때 기존 트랜잭션에 참여하는 것이 아니라 새로운 물리 트랜잭션을 만들어서 시작하게 된다.
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        TransactionStatus inner = txManager.getTransaction(definition);
        log.info("inner.isNewTransaction()={}", inner.isNewTransaction()); // true

        // 로직2가 끝나고 트랜잭션 매니저를 통해 내부 트랜잭션을 롤백한다.
        // 따라서 실제 롤백을 호출한다. (신규 트랜잭션)
        // 트랜잭션이 종료되고, con2 는 종료되거나, 커넥션 풀에 반납된다. -> 이후에 con1 의 보류가 끝나고, 다시 con1 을 사용한다.
        log.info("내부 트랜잭션 롤백");
        txManager.rollback(inner); //롤백

        // 외부 트랜잭션에 커밋을 요청한다.
        // 외부 트랜잭션은 신규 트랜잭션이기 때문에 물리 트랜잭션을 커밋한다.
        // 이때 rollbackOnly 설정을 체크한다. rollbackOnly 설정이 없으므로 커밋한다.
        // 본인이 만든 con1 커넥션을 통해 물리 트랜잭션을 커밋한다. -> 트랜잭션이 종료되고, con1 은 종료되거나, 커넥션 풀에 반납된다
        log.info("외부 트랜잭션 커밋");
        txManager.commit(outer); //커밋
    }
}
