package com.example.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
@Slf4j
@EnableAsync
public class RemoteService {
    @Async(value = "myThreadPool")
    public Callable<String> callable() {
        log.info("callable");
        return () -> {
            log.info("async");
            Thread.sleep(2000);
            return "hello";
        };
    }
}
