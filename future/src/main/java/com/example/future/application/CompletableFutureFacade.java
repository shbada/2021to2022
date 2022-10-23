package com.example.future.application;

import com.example.future.domain.CompletableFutureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CompletableFutureFacade {
    private final CompletableFutureService completableFutureService;

    public int getPrice(String name) {
        return completableFutureService.getPrice(name);
    }

    public CompletableFuture<Integer> getPriceAsync(String name) {
        return completableFutureService.getPriceAsync(name);
    }

    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return completableFutureService.getDiscountPriceAsync(price);
    }
}
