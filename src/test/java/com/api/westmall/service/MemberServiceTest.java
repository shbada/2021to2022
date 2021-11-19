package com.api.westmall.service;

import com.api.westmall.entity.Gender;
import com.api.westmall.entity.Member;
import com.api.westmall.form.MemberForm;
import com.api.westmall.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 등록이 정상적으로 저장되는지 체크")
    @Transactional
    public void add_member_success() throws Exception {
        MemberForm memberForm = new MemberForm();

        String userId = "test001";
        memberForm.setUserId(userId);
        memberForm.setUserName("테스트");
        memberForm.setPassword("root");
        memberForm.setGender(Gender.FEMALE);
        memberForm.setEmail("test@naver.com");

        memberService.saveUser(memberForm);

        Member member = memberRepository.getById(1L);

        Assertions.assertThat(member.getUserId()).isEqualTo(userId);

    }
}