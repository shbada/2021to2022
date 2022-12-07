package com.api.westmall.controller;

import com.api.westmall.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final CommonResponse commonResponse;

    /**
     * output test
     * @return
     */
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return commonResponse.send("hello westmall");
    }
}
