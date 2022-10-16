package com.example.stock_project.facade;

import com.example.stock_project.repository.NamedLockRepository;
import com.example.stock_project.service.NamedLockStockService;
import org.springframework.stereotype.Component;

// lettuce lock
@Component
public class NamedLockStockFacade {

    private final NamedLockRepository namedLockRepository;

    private NamedLockStockService namedLockStockService;

    public NamedLockStockFacade(final NamedLockRepository namedLockRepository, final NamedLockStockService namedLockStockService) {
        this.namedLockRepository = namedLockRepository;
        this.namedLockStockService = namedLockStockService;
    }

    /**
     * StockService 는 부모의 트랜잭션과 별도로 실행되어야하기 때문에 propergation을 별도로 생성해준다.
     * 부모의 트랜잭션과 동일한 범위로 묶인다면 Synchronized 와 같은 문제인 DataBase에 commit 되기전에 락이 풀리는 현상이 발생합니다.
     * 그렇기 때문에 별도의 트랜잭션으로 분리해서 DataBase에 정상적으로 Commit이 된 후에 락을 해제해 주려는 의도르 품고있다.
     * 핵심은 Lock을 해제하기전에 DataBase에 Commit이 되도록 하는것이다.
     */
    public void decrease(Long id, Long quantity) {
        try {
            namedLockRepository.getLock(id.toString());
            namedLockStockService.decrease(id, quantity);
        } finally {
            // 위 로직이 수행된 이후, 락 해제
            namedLockRepository.releaseLock(id.toString());
        }
    }
}
