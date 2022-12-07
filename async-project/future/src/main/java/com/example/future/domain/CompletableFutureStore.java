package com.example.future.domain;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface CompletableFutureStore {
    /** sync */
    int getPrice(String name);
    /** async */
    CompletableFuture<Integer> getPriceAsync(String name);
    CompletableFuture<Integer> getPriceAsyncThread1(String name);
    /** async */
    CompletableFuture<Integer> getDiscountPriceAsync(Integer price);
}
