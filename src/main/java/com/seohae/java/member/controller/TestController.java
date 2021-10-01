package com.seohae.java.member.controller;

import com.seohae.java.common.CommonResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"TestController"})
@RestController
@RequestMapping("/test/")
@RequiredArgsConstructor
public class TestController {

    private final CommonResponse commonResponse;

    @GetMapping("/healthCheck")
    public ResponseEntity<?> status() {
        return commonResponse.send("hello world");
    }
}
