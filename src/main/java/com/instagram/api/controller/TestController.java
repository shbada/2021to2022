package com.instagram.api.controller;

import com.instagram.api.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {
    private final CommonResponse commonResponse;

    /**
     * output test
     * @return
     */
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return commonResponse.send("hello world!");
    }
}
