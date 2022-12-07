package com.api.plan.api.controller;

import com.api.plan.api.repository.MemberRepository;
import com.api.plan.api.service.MemberService;
import com.api.plan.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final CommonResponse commonResponse;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

}
