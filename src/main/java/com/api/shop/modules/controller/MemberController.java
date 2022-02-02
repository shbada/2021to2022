package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"MemberController"})
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final Output output;

    /**
     * 회원 리스트 조회
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<?> getMemberList() {
        return output.send(memberRepository.findAll());
    }
}
