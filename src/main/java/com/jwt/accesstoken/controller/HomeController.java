package com.jwt.accesstoken.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @PreAuthorize("isAuthenticated()") /* gradle security5 */
    @GetMapping("/greeting")
    public String greeting() {
        return "hello";
    }
}
