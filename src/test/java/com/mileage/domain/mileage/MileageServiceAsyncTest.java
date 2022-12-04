package com.mileage.domain.mileage;

import com.mileage.domain.Mileage;
import com.mileage.domain.enums.MileageManageType;
import com.mileage.infrastructure.MileageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MileageServiceAsyncTest {
    @Autowired
    private MileageService mileageService;

    @Autowired
    private MileageRepository mileageRepository;

    final int threadCount = 30;

    /**
     * 3초
     * @throws InterruptedException
     */
    @Test
    void 동기방식_포인트_조회한다() throws InterruptedException {
        // given

        // when
        mileageService.getPointList();

        // then
    }

    /**
     * 2초
     * threadName : ForkJoinPool.commonPool-worker-19
     * threadName : ForkJoinPool.commonPool-worker-5
     * @throws InterruptedException
     */
    @Test
    void 비동기방식_포인트_조회한다() throws InterruptedException {
        // given

        // when
        mileageService.getAsyncPointList();

        // then
    }

    @Test
    void 동기방식_포인트_조회한다_동시요청() throws InterruptedException {
        // given
        int threadCount = 100; // 100 개 요청

        // when
        // thread 100개, 쓰레드풀의 쓰레드 10개, 그러므로 3초 x 10 = 30초
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                    try {
                        mileageService.getPointList();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
        ));

        countDownLatch.await();

        // then
    }

    @Test
    void 비동기방식_포인트_조회한다_동시요청() throws InterruptedException {
        // given
        int threadCount = 100; // 100 개 요청

        // when
        // thread 100개, 쓰레드풀의 쓰레드 10개
        // 해당 메서드가 CompletableFuture을 호출하고 결과값을 받거나(블로킹)하지 않으므로 호출하고 끝
        // 결국 걸리는 시간은 mileageReader.findAll() 을 100개 수행하는 시간일 것
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                    try {
                        mileageService.getAsyncPointList();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
        ));

        countDownLatch.await();

        // then
    }
}