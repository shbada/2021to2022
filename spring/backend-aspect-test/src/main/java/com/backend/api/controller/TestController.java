package com.backend.api.controller;

import com.backend.api.aspect.AroundTarget;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    @AroundTarget
    public String test() {
        return "test";
    }
}
