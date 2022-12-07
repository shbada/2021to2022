package com.example.service.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/step2")
public class TomcatMax1000Controller {
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
