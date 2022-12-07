package com.jpa.bookmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/init")
    public String helloWorld() {
        return "hello world!";
    }
}
