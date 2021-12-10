package com.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    /**
     * 메인화면
     * @return
     */
    @GetMapping("/")
    public String main() {
        return "index";
    }

    /**
     * 로그인
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
