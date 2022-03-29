package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.AddressForm;
import com.api.shop.modules.form.validator.MemberAddFormValidator;
import com.api.shop.modules.form.MemberUpdateForm;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 회원 단건 조회
     * @return
     */
    @GetMapping("/{idx}")
    public ResponseEntity<?> getMember(@PathVariable long idx) {
        Member member = memberService.getMember(idx);
        return output.send(member);
    }

    /**
     * 회원정보 변경
     * @param memberUpdateForm
     * @return
     */
    @PutMapping("/")
    public ResponseEntity<?> putMember(MemberUpdateForm memberUpdateForm) {
        memberService.updateMember(memberUpdateForm);
        return output.send();
    }

    /**
     * 회원 주소 정보 추가
     * @param addressForm
     * @return
     */
    @PutMapping("/{idx}/addr")
    public ResponseEntity<?> putMemberAddr(@PathVariable long idx, AddressForm addressForm) {
        memberService.updateMemberAddr(idx, addressForm);
        return output.send();
    }
}
