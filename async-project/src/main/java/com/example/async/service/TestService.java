package com.example.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@EnableAsync
public class TestService {
    /**
     * 별개의 스레드로 수행시키자.
     * @param req
     * @return
     */
    @Async
    public CompletableFuture<String> work(String req) {
        return CompletableFuture.completedFuture(req + "/asyncwork");
    }
}
