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
class StockServiceTest {
    private final int threadCount = 100;
    private final long productId = 10L;
    private final long quantity = 1L;
    private final long initQuantity = 100L;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @AfterEach
    public void afterEach() {
        stockRepository.deleteAll();
    }

    @Test
    @DisplayName("단일 쓰레드일 때를 테스트한다")
    void 단일_쓰레드() {
        /* setting */
        stockRepository.save(new Stock(productId, initQuantity));

        // given
        // when
        stockService.decreaseV1(productId, quantity);

        // then
        final long afterQuantity = stockRepository.getByProductId(productId).getQuantity();
        assertThat(afterQuantity).isEqualTo(initQuantity - 1);
    }

    /**
     * [테스트 실패]
     * 레이스 컨디션(Race Condition) 발생
     * - 2개 이상의 쓰레드가 공유 데이터에 엑세스할 수 있고, 동시에 변경하려고 할때 발생하는 문제
     * - 같은 데이터를 동시에 변경 (공유된 가변 데이터) 할때, 작업 중 하나가 누락될 수 있다.
     * - (해결방법) : 하나의 스레드가 작업을 완료한 후에, 다른 스레드가 공유된 자원에 접근 가능하도록 해야한다.
     * @throws InterruptedException
     */
    @Test
    @DisplayName("재고가 100개일때 100개의 쓰레드로 테스트한다")
    void 멀티_쓰레드() throws InterruptedException {
        /* setting */
        stockRepository.save(new Stock(productId, initQuantity));
        /**
         * ExecutorService
         * - 병렬 작업시 여러개의 작업을 효율적으로 처리하기 위해 제공되는 JAVA 라이브러리
         * - ThreadPool을 손쉽게 구성하고, Task를 실행하고 관리할 수 있는 역할
         */
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        /**
         * CountDownLatch
         * - 어떤 스레드가 다른 쓰레드에서 작업이 완료될때까지 기다릴 수 있도록 해주는 클래스
         * - 멀티쓰레드가 100번 작업을 모두 완료한 후에 테스트를 하도록 블로킹해준다.
         * 1) new CountDownLatch(latch 갯수)
         * 2) countDown() : latch 숫자 감소
         * 3) await() : latch 숫자가 0이 될때까지 블로킹
         */
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // given
        // when
        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                try {
                    stockService.decreaseV1(productId, quantity);
                } finally {
                    countDownLatch.countDown();
                }
            }
        ));

        countDownLatch.await();

        // then
        final Long afterQuantity = stockRepository.getByProductId(productId).getQuantity();
        System.out.println("동시성 처리 이후 수량 : " + afterQuantity);

        // 100 - (1 * 100) = 0
        assertThat(afterQuantity).isZero();
    }

    /**
     * decreaseV2() : @Transactional 어노테이션 제거
     * [synchronized]
     * - 멀티스레드 환경에서 스레드간 데이터 동기화를 시켜주기 위해서 자바에서 제공하는 키워드
     * - 공유되는 데이터의 Thread-safe를 하기 위해, synchronized 로 스레드간 동기화를 시켜 thread-safe 하게 만든다.
     * - 자바에서 지원하는 synchronized는,  현제 데이터를 사용하고 있는 해당 스레드를 제외하고 나머지 스레드들은 데이터 접근을 막아 순차적으로 데이터에 접근할 수 있도록 해준다.
     * @throws InterruptedException
     */
    @Test
    @DisplayName("synchronized - 동시 100개 테스트")
    void synchronized_사용() throws InterruptedException {
        /* setting */
        stockRepository.save(new Stock(productId, initQuantity));
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        // given
        // when
        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                    try {
                        stockService.decreaseV2(productId, quantity);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
        ));

        countDownLatch.await();

        // then
        final Long afterQuantity = stockRepository.getByProductId(productId).getQuantity();
        System.out.println("synchronized 동시성 처리 이후 수량 : " + afterQuantity);
        assertThat(afterQuantity).isZero();
    }
}