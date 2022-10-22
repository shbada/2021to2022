package com.example.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoteController {
    @GetMapping("/service")
    public String service(String req) throws InterruptedException {
        Thread.sleep(2000);
        return req + "/service";
    }

    @GetMapping("/service2")
    public String service2(String req) throws InterruptedException {
        return req + "/service2";
    }
}
