package com.example.orderservice.controller;

import com.example.orderservice.common.CommonResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"OrderController"})
@RestController
@RequestMapping("/order/")
@RequiredArgsConstructor
public class OrderController {

    private final CommonResponse commonResponse;
    private final Environment env;

    @GetMapping("/healthCheck")
    public ResponseEntity<?> status() {
        return commonResponse.send(String.format("It's Working in User Service on Port %s", env.getProperty("local.server.port")));
    }
}
