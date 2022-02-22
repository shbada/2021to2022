package com.api.shop.modules.controller;

import com.api.shop.config.security.Role;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.repository.MemberRepository;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

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

    @DisplayName("로그인_통과")
    @Test
    void login_통과() throws Exception {
        String requestMemberName = "seohae";

        /* step1. 회원가입 */
        mockMvc.perform(post("/auth/register")
                        .param("memberName", requestMemberName)
                        .param("password", "12341234"))
                .andExpect(status().isOk());

        /* step2. 로그인 */
        MvcResult result = mockMvc.perform(post("/auth/login")
                        .param("memberName", requestMemberName)
                        .param("password", "12341234"))
                .andExpect(status().isOk())
                .andReturn();

        /* get result : login MemberName */
        String response = result.getResponse().getContentAsString();
        String memberName = JsonPath.parse(response).read("$.body");

        /* memberName check */
        Assertions.assertThat(requestMemberName).isEqualTo(memberName);

        /* member 가져와서 Role check */
        Optional<Member> byMemberName = memberRepository.findByMemberName(memberName);
        Assertions.assertThat(byMemberName.get().getRole()).isEqualTo(Role.ROLE_USER);
    }
}