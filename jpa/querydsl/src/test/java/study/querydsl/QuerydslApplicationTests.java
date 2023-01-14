package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {
    @Autowired // Spring
//    @PersistenceContext // 표준 스펙
    EntityManager em;

    @Test
    void contextLoads() {
        Hello hello = new Hello();
        em.persist(hello);

        JPAQueryFactory query = new JPAQueryFactory(em);
//        QHello qHello = new QHello("h"); // Querydsl Q타입 동작 확인
        QHello qHello = QHello.hello; // Querydsl Q타입 동작 확인

        // Querydsl 사용
        Hello result = query
                .selectFrom(qHello) // Q클래스 사용해야한다.
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(hello);
        // lombok 동작 확인 (hello.getId())
        Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
    }
}
