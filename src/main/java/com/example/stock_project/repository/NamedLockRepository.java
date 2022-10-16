package com.example.stock_project.repository;

import com.example.stock_project.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// lettuce lock
public interface NamedLockRepository extends JpaRepository<Stock, Long> {

    /**
     * [Named Lock]
     * - 이름을 가진 metadata locking
     * - 이름을 가진 lock 을 획득한 후 해제할 때 까지 다른 세션은 이 lock 을 획득할 수 없다.
     * - 주의할 점으로는 transaction 이 종료될때 lock 이 자동으로 해제되지 않는다.
     * - 별도의 명령어로 해제를 수행해주거나 선점시간이 끝나야 해제된다.
     * - Mysql 에서는 getLock( ) 을 통해 획들 /
     *
     * (점유과정)
     * 1) Named Lock은 Stock에 락을 걸지 않고, 별도의 공간에 락을 건다.
     * 2) session-1 이 1이라는 이름으로 락을 건다면, session 1 이 1을 해지한 후에 락을 얻을 수 있다.
     *
     * (단점)
     * 예제에서는 동일한 DataSource 를 사용하지만, 실제 서비스에서는 커넥션풀이 부족해질 수 있기에 DataSoruce 를 분리하는 걸 추천한다고 한다..
     * 예제에서는 편의성을 위해 Stock 엔티티를 사용했지만, 실무에서도 별도의 JDBC를 사용해야한다!
     */
    @Query(value = "select get_lock(:key, 1000)", nativeQuery = true)
    void getLock(String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock(String key);
}
