package com.example.async.application;

import com.example.async.domain.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
public class RestTemplateFacade {
    private final RestTemplateService restTemplateService;

    public ListenableFuture<String> work(String req) {
        return restTemplateService.work(req);
    }

    public void loadTest(String url) throws InterruptedException {
        restTemplateService.loadTest(url);
    }
}
