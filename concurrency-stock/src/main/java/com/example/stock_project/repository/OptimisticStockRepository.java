package com.example.stock_project.repository;

import com.example.stock_project.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface OptimisticStockRepository extends JpaRepository<Stock, Long> {
    /**
     * [Optimisitc Lock]
     * - 실제로 Lock 을 이용하지 않고 버전을 이용함으로써 정합성을 맞추는 방법이다.
     * - 먼저 데이터를 읽은 후에 update 를 수행할 떄 현재 내가 읽은 버전이 맞는지 확인하며 업데이트한다.
     * - 자원에 락을 걸어서 선점하지 않고, 동시성 문제가 발생하면 그때가서 처리하는 낙관적 락 방식입니다.
     * - 내가 읽은 버전에서 수정사항이 생겼을 경우에는 application에서 다시 읽은 후에 작업을 수행하는 롤백 작업을 수행해야한다.
     *
     * (수행과정)
     * 1) 서버 1이 version1 임을 조건절에 명시하면서 업데이트 쿼리 요청
     * 2) version1 쿼리가 업데이트 되어서, 디비는 version 2로 업데이트
     * 3) server2 가 version1 로 업데이트 쿼리를 날리면 버전이 맞지않아 실패
     * 4) 쿼리가 실패하면 server2 에서 다시 조회하여 버전을 맞춘 후 업데이트 쿼리 요청
     *
     * Stock.java - version 필드 추가
     * (장점)
     * 충돌이 안난다는 가정하에, 별도의 락을 잡지 않으므로 Pessimistic Lock 보다는 성능적 이점을 가진다.
     *
     * (단점)
     * 업데이트가 실패했을 떄, 재시도 로직을 개발자가 직접 작성해줘야한다.
     * 충돌이 빈번하게 일어나거나 예상이되면, 롤백 처리를 해주어야하기 때문에 Pessimistic Lock 이 더 성능이 좋을 수도 있다.
     */
    @Lock(LockModeType.OPTIMISTIC)
    Stock getByProductId(Long productId);
}
