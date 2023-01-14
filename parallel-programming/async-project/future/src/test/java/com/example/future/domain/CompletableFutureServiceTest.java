package com.example.future.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CompletableFutureServiceTest {
    @Autowired
    private CompletableFutureService completableFutureService;

    @Test
    void 가격조회_동기() {
        int expectedPrice = 1100;

        int resultPrice = completableFutureService.getPrice("latte");
        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void 가격조회_비동기_블로킹() {
        int expectedPrice = 1100;

        CompletableFuture<Integer> future = completableFutureService.getPriceAsync("latte");

        log.info("아직 결과 받기 전...다른 작업 수행 가능");
        int resultPrice = future.join(); // 블로킹
        log.info("최종 가격 전달 받음");

        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void 가격조회_비동기_반환없음() {
        int expectedPrice = 1100;

        CompletableFuture<Void> future = completableFutureService
                .getPriceAsync("latte")
                        .thenAccept(p -> {
                            log.info("콜백 - price : " + p + " 원 - 데이터를 반환하지는 않음");
                            assertEquals(expectedPrice, p);
                        });

        log.info("아직 최종 데이터는 전달받지 않았고, 다른 작업 수행 가능하다. - 논블로킹");

        /**
         * 테스트 코드에서는 main 쓰레드 종료시, thenAccept 확인 전에 종료된다.
         * 테스트 코드는 Main 쓰레드에서 동작하게 되고, thenAccept 콜백 메서드가 수행되기도 전에 Main 쓰레드는 종료된다.
         * 그래서 main 쓰레드가 종료되지 않도록 블로킹으로 대기한다.
         */
        assertNull(future.join());
    }

    @Test
    void 가격조회_비동기_반환있음() {
        int expectedPrice = 1100 + 1000;

        CompletableFuture<Void> future = completableFutureService
                .getPriceAsync("latte")
                .thenApply(p -> {
                    log.info("같은 쓰레드에서 동작");
                    return p + 1000;
                })
                .thenAccept(p -> {
                    log.info("콜백 - price : " + p + " 원 - 데이터를 반환하지는 않음");
                    assertEquals(expectedPrice, p);
                });

        log.info("아직 최종 데이터는 전달받지 않았고, 다른 작업 수행 가능하다. - 논블로킹");
    }

    @Test
    void thenCombine_test() {
        int expectedPrice = 1100 + 1300;

        // future1, future2 는 서로 다른 스레드로 수행된다.
        CompletableFuture<Integer> future1 = completableFutureService.getPriceAsync("latte");
        CompletableFuture<Integer> future2 = completableFutureService.getPriceAsync("mocha");

        Integer resultPrice = future1.thenCombine(future2, Integer::sum).join();

        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void thenCombine_test_쓰레드풀_1개() {
        int expectedPrice = 1100 + 1300;

        // future1, future2 는 동일한 스레드로 수행된다. 쓰레드풀이 1이기 때문이다.
        CompletableFuture<Integer> future1 = completableFutureService.getPriceAsyncThread1("latte");
        CompletableFuture<Integer> future2 = completableFutureService.getPriceAsyncThread1("mocha");

        Integer resultPrice = future1.thenCombine(future2, Integer::sum).join();

        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void getDiscountPriceAsync_thenCompose() {
        Integer expectedPrice = (int)(1100 * 0.9);

        /*
            순차적 수행
            1) 가격 조회
            2) 조회된 가격에 할인율 적용 
         */
        CompletableFuture<Integer> future1 = completableFutureService.getPriceAsync("latte");

        Integer resultPrice = future1.thenCompose(result -> completableFutureService.getDiscountPriceAsync(result)).join();

        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    void getDiscountPriceAsync_allOf() {
        Integer expectedPrice = 1100 + 1300 + 900;
        
        CompletableFuture<Integer> future1 = completableFutureService.getPriceAsync("latte");
        CompletableFuture<Integer> future2 = completableFutureService.getPriceAsync("mocha");
        CompletableFuture<Integer> future3 = completableFutureService.getPriceAsync("americano");

        List<CompletableFuture<Integer>> completableFutureList = Arrays.asList(future1, future2, future3);

        Integer resultPrice = CompletableFuture.allOf(future1, future2, future3)
                        .thenApply(Void -> completableFutureList.stream()
                                .map(CompletableFuture::join)
                                .collect(Collectors.toList()))
                        .join() // [1200, 1300, 900]
                        .stream()
                        .reduce(0, Integer::sum);

        assertEquals(expectedPrice, resultPrice);
    }
}