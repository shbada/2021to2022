package com.seohae.java.member.controller;

import com.seohae.java.common.CommonResponse;
import com.seohae.java.member.dto.MemberDto;
import com.seohae.java.member.dto.entity.Member;
import com.seohae.java.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"MemberController"})
@RestController
@RequestMapping("/member/")
@RequiredArgsConstructor
public class MemberController {
    private final CommonResponse commonResponse;
    private final MemberService memberService;

    /**
     * Member 단건 등록
     * @param memberDto
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addMember(@ModelAttribute MemberDto memberDto) {
        /* 1. users */
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); /* 강력하게 */

        Member member = mapper.map(memberDto, Member.class);
        Long userIdx = memberService.addMember(member);

        return commonResponse.send(userIdx);
    }

    /**
     * userName 에 해당하는 멤버 리스트 조회
     * @param memberDto
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getMemberName(@ModelAttribute MemberDto memberDto) {
        /* 1. userName 에 해당하는 멤버 리스트 조회 */
        List<MemberDto> memberDtoList = memberService.getMemberNameList(memberDto.getUserName());

        return commonResponse.send(memberDtoList);
    }
}
