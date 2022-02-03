package com.api.shop.modules.controller;

import com.api.shop.modules.entity.Member;
import com.api.shop.modules.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원 가입 validation 체크")
    @Test
    void register_validation_미통과() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("memberName", "seohae")
                        .param("password", "1234512345"))
                .andExpect(status().isOk());

        List<Member> member = memberRepository.findAll();
        Assertions.assertThat(member.size()).isEqualTo(1);
    }
}