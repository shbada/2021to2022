package com.test.technology.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test-api", url = "${feign.test-api.url}")
public interface TestClient {

    @GetMapping("/test")
    String authTest();
}
