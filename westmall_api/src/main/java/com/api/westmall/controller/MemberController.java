package com.api.westmall.controller;

import com.api.westmall.common.CommonResponse;
import com.api.westmall.form.MemberForm;
import com.api.westmall.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final CommonResponse commonResponse;
    private final MemberService memberService;

    @PostMapping("")
    public ResponseEntity<?> user(@ModelAttribute @Valid MemberForm memberForm) {
        memberService.saveUser(memberForm);

        return commonResponse.send();
    }

}
