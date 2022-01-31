package com.api.shop.api.controller;

import com.api.shop.api.repository.MemberRepository;
import com.api.shop.api.service.MemberService;
import com.api.shop.common.Output;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private MemberRepository memberRepository;
    private MemberService memberService;
    private Output output;

    /**
     * 회원가입
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> memberRegister() {
        return output.send();
    }
}
