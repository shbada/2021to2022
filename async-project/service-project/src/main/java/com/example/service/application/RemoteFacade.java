package com.example.service.application;

import com.example.service.domain.RemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
@RequiredArgsConstructor
public class RemoteFacade {
    private final RemoteService remoteService;

    public Callable<String> callable() {
        return remoteService.callable();
    }
}
