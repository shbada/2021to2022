package com.seohae.java.controller;

import com.seohae.java.common.CommonResponse;
import com.seohae.java.dto.MemberDto;
import com.seohae.java.dto.entity.Member;
import com.seohae.java.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"MemberController"})
@RestController
@RequestMapping("/member/")
@RequiredArgsConstructor
@Slf4j
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
        /* 1. Member List 조회 */
        List<MemberDto> memberAllList = memberService.getMemberList();

        /* 2. userName 에 해당하는 멤버 리스트 조회 */
        List<MemberDto> memberList = memberService.getMemberNameFilterList(memberDto.getUserName(), memberAllList);

        /* 3. 첫번째 멤버 추출하기 */
        MemberDto memberDto1 = memberService.getMemberFirst(memberList);
        log.info(memberDto1.getUserId());

        return commonResponse.send(memberList);
    }

    /**
     * userName 으로만 이루어진 리스트 추출
     * @return
     */
    @GetMapping("/map")
    public ResponseEntity<?> getMemberNameMapList() {
        /* 1. Member List 조회 */
        List<MemberDto> memberAllList = memberService.getMemberList();

        /* 2. userName 에 해당하는 멤버 리스트 조회 */
        List<String> memberList = memberService.getMemberNameMapList(memberAllList);

        return commonResponse.send(memberList);
    }
}
