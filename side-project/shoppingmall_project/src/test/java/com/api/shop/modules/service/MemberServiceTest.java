package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.AddressForm;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.form.MemberUpdateForm;
import com.api.shop.modules.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @DisplayName("회원 정보 업데이트 실패 - 존재하지 않는 회원")
    @Test
    void update_member_fail() throws Exception {
        MemberUpdateForm memberUpdateForm = new MemberUpdateForm();
        memberUpdateForm.setIdx(1000000L);
        memberUpdateForm.setPassword("123123123");

        /* BadRequestException 발생 */
        assertThrows(BadRequestException.class, () -> {
            memberService.updateMember(memberUpdateForm);
        });
    }

    @DisplayName("회원 정보 업데이트 성공")
    @Test
    void update_member_success() throws Exception {
        long memberIdx = registerMember();
        String password = "12341234";

        MemberUpdateForm memberUpdateForm = new MemberUpdateForm();
        memberUpdateForm.setIdx(memberIdx);
        memberUpdateForm.setPassword(password);

        memberService.updateMember(memberUpdateForm);

        /* 업데이트된 정보로 조회 */
        Member updatedMember = memberRepository.getById(memberIdx);

        /* 패스워드 체크 */
        assertTrue(passwordEncoder.matches(password, updatedMember.getPassword()));
    }

    /**
     * 회원 등록
     * @return
     */
    private long registerMember() {
        MemberAddForm memberAddForm = new MemberAddForm();
        memberAddForm.setMemberName("kimseohae");
        memberAddForm.setPassword("1234512345");

        return memberService.saveMember(memberAddForm);
    }

    /**
     * 회원 주소 정보 업데이트
     * @throws Exception
     */
    @DisplayName("회원 주소 정보 업데이트 성공")
    @Test
    void update_member_address_success() throws Exception {
        long memberIdx = registerMember();

        AddressForm addressForm = new AddressForm();
        addressForm.setCity("addr1");
        addressForm.setStreet("addr2");
        addressForm.setZipcode("12345");

        memberService.updateMemberAddr(memberIdx, addressForm);

        /* 업데이트된 정보로 조회 */
        Member updatedMember = memberRepository.getById(memberIdx);

        /* 패스워드 체크 */
        assertEquals(updatedMember.getIdx(), memberIdx);
        assertEquals(updatedMember.getAddress().getCity(), addressForm.getCity());
        assertEquals(updatedMember.getAddress().getStreet(), addressForm.getStreet());
        assertEquals(updatedMember.getAddress().getZipcode(), addressForm.getZipcode());
    }
}