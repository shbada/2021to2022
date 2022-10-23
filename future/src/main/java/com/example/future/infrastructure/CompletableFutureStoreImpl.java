package com.example.future.infrastructure;

import com.example.future.domain.CompletableFutureStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompletableFutureStoreImpl implements CompletableFutureStore {
    private final CompletableFutureRepository completableFutureRepository;
    private final Executor executor_10 = Executors.newFixedThreadPool(10);
    private final Executor executor_1 = Executors.newFixedThreadPool(1);

    @Override
    public int getPrice(String name) {
        log.info("동기 호출 방식");
        return completableFutureRepository.getPriceByName(name);
    }

    @Override
    public CompletableFuture<Integer> getPriceAsync(String name) {
        log.info("비동기 호출 방식");

        /**
         * CompletableFuture
         * 1) supplyAsync : Supplier 파라미터
         * 2) runAsync : Runnable 파라미터
         *
         * ForkJoinPool의 commonPool 사용
         */
//        return CompletableFuture.supplyAsync(() -> completableFutureRepository.getPriceByName(name));
//        CompletableFuture<Integer> future = new CompletableFuture<>();
//        new Thread(() -> { // Thread-4 (다른 쓰레드로 작업)
//            log.info("새로운 쓰레드로 작업");
//            Integer price = completableFutureRepository.getPriceByName(name);
//            future.complete(price);
//        }).start();
//        return future;

        // commonPool 을 사용하는 방법은 바람직하지 않다. executor 파라미터를 추가하자.
        return CompletableFuture.supplyAsync(() -> {
            log.info("async");
            return completableFutureRepository.getPriceByName(name);
        }, executor_10);
    }

    @Override
    public CompletableFuture<Integer> getPriceAsyncThread1(String name) {
        log.info("비동기 호출 방식");

        return CompletableFuture.supplyAsync(() -> {
            log.info("async");
            return completableFutureRepository.getPriceByName(name);
        }, executor_1);
    }

    @Override
    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("supplyAsync");
            return (int)(price * 0.9);
        }, executor_10);
    }
}
