package com.example.stock_project.service;

import com.example.stock_project.domain.Stock;
import com.example.stock_project.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * 멀티쓰레드 환경에서 동시성 이슈 발생
     * [synchronized + @Transactional]
     * - 일단 결론적으로 decreaseV2() 처럼 @Transactional 가 없고 syncrhonized 키워드만 있을때 테스트가 성공한다.
     *
     * save() 메서드는 데이터베이스에 바로 flush() 해주는 로직이 아니다.
     * @Transactional의 동작방식 문제인데, 이 때문에 DB에 값이 입력되기 전에 다른 스레드가 메서드에 접근이 가능하다.
     */
    @Transactional
    public synchronized void decreaseV1(final Long id, final Long quantity) {
        // get stock
        final Stock stock = stockRepository.getByProductId(id);

        // decrease stock
        stock.decrease(quantity);
    }

    /**
     * @Transactional 을 제거하고 Repository 에서 save, flush 를 모두 수동으로 진행한다.
     *
     * synchronized 키워드
     * - 같은 프로세스 단위에서만 동시성을 보장한다.
     * - 따라서 서버가 1대일 떄는 동시성 이슈가 해결되는 듯 하나, 여러대의 서버를 활용하면 여러개의 인스턴스가 존재하는것과 동일하므로
     *   웹 환경에서는 동시성을 보장하지 못한다.
     *
     * 하지만 synchronized도 완벽한 해결방안은 될 수 없다.
     * 서버가 여러대 일 경우, Synchronized 는 각 프로세스의 동시접근 제어만을 보장해주기 때문에
     * 다른 서버에서 가변 공유데이터에 접근하는 것을 막을 수 가 없어, 업데이트 도중 값이 변경될 수 있다.
     */
    public synchronized void decreaseV2(final Long id, final Long quantity) {
        // get stock
        final Stock stock = stockRepository.getByProductId(id);

        // decrease stock
        stock.decrease(quantity);

        // save stock
        stockRepository.saveAndFlush(stock);
    }
}
