package com.example.stock_project.facade;

import com.example.stock_project.domain.Stock;
import com.example.stock_project.facade.LettuceLockStockFacade;
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
class LettuceLockStockFacadeTest {
    private final int threadCount = 100;
    private final long productId = 10L;
    private final long quantity = 1L;
    private final long initQuantity = 100L;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LettuceLockStockFacade lettuceLockStockFacade;

    @AfterEach
    public void afterEach() {
        stockRepository.deleteAll();
    }

    // Redis를 사용하면 트랜잭션에 따라 대응되는 현재 트랜잭션 풀 세션 관리를 하지 않아도 되므로 구현이 편리하다.
    // Spin Lock 방식이므로 부하를 줄 수 있어서 thread busy waiting을 통하여 요청 간의 시간을 주어야 한다.
    @Test
    @DisplayName("redis lettuce lock 사용 - 동시에 100개 테스트")
    void redis_lettuce_lock() throws InterruptedException {
        /* setting */
        stockRepository.save(new Stock(productId, initQuantity));
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // given
        // when
        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                    try {
                        lettuceLockStockFacade.decrease(productId, quantity);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
        ));

        countDownLatch.await();

        // then
        final Long afterQuantity = stockRepository.getByProductId(productId).getQuantity();
        System.out.println("redis_lettuce_lock 동시성 처리 이후 수량 : " + afterQuantity);
        assertThat(afterQuantity).isZero();
    }
}