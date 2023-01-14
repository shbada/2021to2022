package com.api.plan.api.controller;

import com.api.plan.api.dto.MemberDto;
import com.api.plan.api.entity.Member;
import com.api.plan.api.service.AuthService;
import com.api.plan.api.service.MemberService;
import com.api.plan.common.exception.BadRequestException;
import com.api.plan.common.response.CommonResponse;
import com.api.plan.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final CommonResponse commonResponse;
    private final AuthService authService;
    private final MemberService memberService;

    /**
     * 사용자 정보 조회
     * @param principalDetails
     * @return
     */
    @GetMapping("/load")
    public ResponseEntity<?> loadUser(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails != null) { // 사용자가 있을 경우 사용자 정보를 리턴
            Member member = principalDetails.getMember();

            MemberDto userDto = MemberDto.builder()
                    .idx(member.getIdx())
                    .memberId(member.getMemberId())
                    .memberName(member.getMemberName())
                    .gender(member.getGender())
                    .age(member.getAge())
                    .jobName(member.getJobName())
                    .role(member.getRole())
                    .build();

            return commonResponse.send(userDto);
        } else {
            throw new BadRequestException("NOT FOUND USER");
        }
    }
}
