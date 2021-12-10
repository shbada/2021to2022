package com.login.controller;

import com.login.form.LoginForm;
import com.login.repository.UserRepository;
import com.login.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

//    @PostMapping("/login")
//    public String signUpSubmit(@Valid LoginForm loginForm, Errors errors) {
//        /* error 발생시 다시 화면으로 돌아간다 */
//        if (errors.hasErrors()) {
//            return "login";
//        }
//
//        /* 로그인 */
//        accountService.login(loginForm);
//
//        /* 로그인 완료시, 메인페이지로 이동 */
//        return "redirect:/";
//    }
}
