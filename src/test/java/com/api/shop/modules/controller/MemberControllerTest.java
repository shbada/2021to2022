package com.api.shop.modules.controller;

import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @DisplayName("회원 리스트 조회")
    @Test
    void get_member_list() throws Exception {
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(System.out::println);
    }

    @DisplayName("회원 단건 조회")
    @Test
    void get_member() throws Exception {
        long idx = registerMember();
        Member member = memberService.getMember(idx);
        assertEquals(member.getIdx(), idx);
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
}