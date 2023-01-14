package com.example.stock_project.service;

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
class PressimisticLockStockServiceTest {
    private final int threadCount = 100;
    private final long productId = 10L;
    private final long quantity = 1L;
    private final long initQuantity = 100L;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PessimisticLockStockService pessimisticLockStockService;

    @AfterEach
    public void afterEach() {
        stockRepository.deleteAll();
    }

    @Test
    @DisplayName("pessimistic_lock 사용 - 동시에 100개 테스트")
    void pessimistic_lock() throws InterruptedException {
        /* setting */
        stockRepository.save(new Stock(productId, initQuantity));
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // given
        // when
        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                try {
                    pessimisticLockStockService.decrease(productId, quantity);
                } finally {
                    countDownLatch.countDown();
                }
            }
        ));

        // 임의의 쓰레드가 다른 쓰레드의 작업이 종료될때까지 기다린다.
        countDownLatch.await();

        // then
        final Long afterQuantity = stockRepository.getByProductId(productId).getQuantity();
        System.out.println("pessimistic_lock 동시성 처리 이후 수량 : " + afterQuantity);
        assertThat(afterQuantity).isZero();
    }
}