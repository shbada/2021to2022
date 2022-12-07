package com.example.async.application;

import com.example.async.domain.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class TestFacade {
    private final TestService testService;

    public CompletableFuture<String> work(String req) {
        return testService.work(req);
    }
}
