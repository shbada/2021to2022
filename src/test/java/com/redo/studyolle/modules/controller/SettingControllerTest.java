package com.redo.studyolle.modules.controller;

import com.redo.studyolle.infra.MockMvcTest;
import com.redo.studyolle.infra.WithAccount;
import com.redo.studyolle.modules.domain.entity.Account;
import com.redo.studyolle.modules.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@MockMvcTest
class SettingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @WithAccount("seohae")
    @DisplayName("패스워드 수정 폼")
    @Test
    void updatePassword_form() throws Exception {
        mockMvc.perform(get("/settings/password"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("passwordForm"));
    }

    @WithAccount("seohae")
    @DisplayName("패스워드 수정 - 입력값 정상")
    @Test
    void updatePassword_success() throws Exception {
        mockMvc.perform(post("/settings/password")
                        .param("newPassword", "12345678")
                        .param("newPasswordConfirm", "12345678")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/settings/password"))
                .andExpect(flash().attributeExists("message"));

        Account seohae = accountRepository.findByNickname("seohae");
        assertTrue(passwordEncoder.matches("12345678", seohae.getPassword()));
    }

    @WithAccount("seohae")
    @DisplayName("패스워드 수정 - 입력값 에러 - 패스워드 불일치")
    @Test
    void updatePassword_fail() throws Exception {
        mockMvc.perform(post("/settings/password")
                        .param("newPassword", "12345678")
                        .param("newPasswordConfirm", "11111111")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/password"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("passwordForm"))
                .andExpect(model().attributeExists("account"));
    }

    @WithAccount("seohae")
    @DisplayName("닉네임 수정 폼")
    @Test
    void updateAccountForm() throws Exception {
        mockMvc.perform(get("/settings/account"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("nicknameForm"));
    }

    @WithAccount("seohae")
    @DisplayName("닉네임 수정하기 - 입력값 정상")
    @Test
    void updateAccount_success() throws Exception {
        String newNickname = "westssun";
        mockMvc.perform(post("/settings/account")
                        .param("nickname", newNickname)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/settings/account"))
                .andExpect(flash().attributeExists("message"));

        assertNotNull(accountRepository.findByNickname("westssun"));
    }

    @WithAccount("seohae")
    @DisplayName("닉네임 수정하기 - 입력값 에러")
    @Test
    void updateAccount_failure() throws Exception {
        String newNickname = "¯\\_(ツ)_/¯";
        mockMvc.perform(post("/settings/account")
                        .param("nickname", newNickname)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/account"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("nicknameForm"));
    }

    @WithAccount("seohae")
    @DisplayName("프로필 수정 폼")
    @Test
    void updateProfileForm() throws Exception {
        mockMvc.perform(get("/settings/profile"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profileForm"));
    }

    @WithAccount("seohae")
    @DisplayName("프로필 수정하기 - 입력값 정상")
    @Test
    void updateProfile() throws Exception {
        String bio = "짧은 소개를 수정하는 경우.";
        mockMvc.perform(post("/settings/profile")
                        .param("bio", bio)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/settings/profile"))
                .andExpect(flash().attributeExists("message"));

        Account seohae = accountRepository.findByNickname("seohae");
        assertEquals(bio, seohae.getBio());
    }

    @WithAccount("seohae")
    @DisplayName("프로필 수정하기 - 입력값 에러")
    @Test
    void updateProfile_error() throws Exception {
        String bio = "길게 소개를 수정하는 경우. 길게 소개를 수정하는 경우. 길게 소개를 수정하는 경우. 너무나도 길게 소개를 수정하는 경우. ";
        mockMvc.perform(post("/settings/profile")
                        .param("bio", bio)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("settings/profile"))
                .andExpect(model().attributeExists("account"))
                .andExpect(model().attributeExists("profileForm"))
                .andExpect(model().hasErrors());

        Account seohae = accountRepository.findByNickname("seohae");
        assertNull(seohae.getBio());
    }
}