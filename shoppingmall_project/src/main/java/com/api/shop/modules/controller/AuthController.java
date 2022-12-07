package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.form.LoginForm;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.form.validator.MemberAddFormValidator;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.service.AuthService;
import com.api.shop.modules.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"AuthController"})
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final AuthService authService;
    private final MemberAddFormValidator memberAddFormValidator;
    private final Output output;

    @InitBinder("memberAddForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(memberAddFormValidator);
    }

    /**
     * 회원가입
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute MemberAddForm memberAddForm) {
        Long idx = memberService.saveMember(memberAddForm);
        return output.send(idx);
    }

    /**
     * 로그인
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @ModelAttribute LoginForm loginForm) {
        String memberName = authService.login(loginForm);
        return output.send(memberName);
    }
}
