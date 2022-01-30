package com.api.shop.api.controller;

import com.api.shop.api.repository.MemberRepository;
import com.api.shop.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private MemberRepository memberRepository;
    private MemberService memberService;
}
