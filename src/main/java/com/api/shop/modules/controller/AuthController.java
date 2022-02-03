package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.form.MemberForm;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"AuthController"})
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final Output output;

    /**
     * 회원가입
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> memberRegister(@Valid @ModelAttribute MemberForm memberForm) {
        memberService.saveMember(memberForm);
        return output.send();
    }
}
