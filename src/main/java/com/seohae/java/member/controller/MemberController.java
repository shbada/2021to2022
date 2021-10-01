package com.seohae.java.member.controller;

import com.seohae.java.common.CommonResponse;
import com.seohae.java.member.dto.MemberDto;
import com.seohae.java.member.dto.entity.Member;
import com.seohae.java.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"MemberController"})
@RestController
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {
    private final CommonResponse commonResponse;
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<?> addMember(@ModelAttribute MemberDto memberDto) {
        /* 1. users */
        Member member = new Member(memberDto.getUserId(), memberDto.getUserName(), memberDto.getUserAge(), memberDto.getUserSex());
        Long userIdx = memberService.addMember(member);

        return commonResponse.send(userIdx);
    }
}
