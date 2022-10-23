package com.example.future.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class CompletableFutureService {
    private final CompletableFutureStore completableFutureStore;

    public int getPrice(String name) {
        return completableFutureStore.getPrice(name);
    }

    public CompletableFuture<Integer> getPriceAsync(String name) {
        return completableFutureStore.getPriceAsync(name);
    }

    public CompletableFuture<Integer> getPriceAsyncThread1(String name) {
        return completableFutureStore.getPriceAsyncThread1(name);
    }

    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return completableFutureStore.getDiscountPriceAsync(price);
    }
}
