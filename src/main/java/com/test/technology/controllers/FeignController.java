package com.test.technology.controllers;

import com.test.technology.commons.Output;
import com.test.technology.feign.TestClient;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {"FeignController"})
@RestController
@RequestMapping("/feign")
@Slf4j
public class FeignController {

    private final Output output;
    private final TestClient testClient;

    public FeignController(Output output, TestClient testClient) {
        this.output = output;
        this.testClient = testClient;
    }

    @GetMapping("")
    public ResponseEntity<?> getFeignConnect(HttpServletRequest request) {
        String result = testClient.authTest();
        log.info("결과 ::: " + result);
        return output.send(result);
    }
}
