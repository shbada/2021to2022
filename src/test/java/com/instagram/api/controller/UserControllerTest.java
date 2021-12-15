package com.instagram.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("회원가입 성공")
    @Test
    void addUser_success() throws Exception {
        UserJoinReqDto userJoinReqDto = UserJoinReqDto.builder()
                        .username("kimseohae")
                        .password("1234")
                        .email("test@test.com")
                        .name("김서해")
                        .build();

        String param = objectMapper.writeValueAsString(userJoinReqDto);
        mockMvc.perform(post("/user/join")
                        .content(param)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertTrue(userRepository.existsByUsername("kimseohae"));
    }
}