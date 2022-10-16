package com.example.stock_project.facade;

import com.example.stock_project.domain.Stock;
import com.example.stock_project.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NamedLockStockFacadeTest {
    private final int threadCount = 100;
    private final long productId = 10L;
    private final long quantity = 1L;
    private final long initQuantity = 100L;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private NamedLockStockFacade namedLockStockFacade;

    @AfterEach
    public void afterEach() {
        stockRepository.deleteAll();
    }

    // 데이터 소스를 분리하지 않고 하나로 사용할 경우 커넥션 풀이 부족해질 수 있으므로 분리하는 것을 추천한다.
    @Test
    @DisplayName("named_lock 사용 - 동시에 100개 테스트")
    void named_lock() throws InterruptedException {
        /* setting */
        stockRepository.save(new Stock(productId, initQuantity));
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // given

        // when
        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                try {
                    namedLockStockFacade.decrease(productId, quantity);
                } finally {
                    countDownLatch.countDown();
                }
            }
        ));

        countDownLatch.await();

        // then
        final Long afterQuantity = stockRepository.getByProductId(productId).getQuantity();
        System.out.println("NAMED LOCK 동시성 처리 이후 수량 : " + afterQuantity);
        assertThat(afterQuantity).isZero();
    }
}