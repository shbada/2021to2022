package com.example.stock_project.repository;

import com.example.stock_project.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

/**
 * Pessimistic Lock
 */
public interface PessimisticStockRepository extends JpaRepository<Stock, Long> {

    /**
     * [Pessimistic Lock]
     * - 데이터에는 락을 가진 스레드만 접근이 가능하도록 제어하는 방법
     * - 실제로 데이터에 Lock을 걸어서 정합성을 맞추는 방법이다.
     * - exclusive lock(베타적 잠금)을 걸게되면 다른 트랜잭션에서는 lock 이 해제되기 전에 데이터를 가져갈 수 없다.
     * - 자원 요청에 따른 동시성문제가 발생할 것이라고 예상하고 락을 걸어버리는 비관적 락 방식입니다.
     * - 데드락이 걸릴 수 있는 단점이 있다.
     *
     * (장점)
     * 충돌이 빈번하게 일어난다면 롤백의 횟수를 줄일 수 있기 때문에, Optimistic Lock 보다는 성능이 좋을 수 있다.
     * 비관적 락을 통해 데이터를 제어하기 때문에 데이터 정합성을 어느정도 보장할 수 있다.
     *
     * (단점)
     * 데이터 자체에 별도의 락을 잡기때문에 동시성이 떨어져 성능저하가 발생할 수 있다.
     * 특히 읽기가 많이 이루어지는 데이터베이스의 경우에는 손해가 더 크다.
     * 서로 자원이 필요한 경우, 락이 걸려있으므로 데드락이 일어날 가능성이 있다.
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Stock getByProductId(Long productId);
}