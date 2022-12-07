package com.instagram.api.controller;

import com.instagram.api.IntegrationTest;
import com.instagram.api.config.jwt.JwtEnum;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.dto.UserLoginReqDto;
import com.instagram.api.repository.UserRepository;
import com.instagram.api.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * (webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
 * 내장 서버 랜덤 포트로 띄운다.
 * 기본값 : SpringBootTest.WebEnvironment.MOCK
 * 위처럼 설정한 이유는 실제로 테스트를 위한 서블릿 컨테이너를 띄우기 위해다.
 */
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    @DisplayName("로그인 유저 조회 성공")
    @Test
    void getLoginUser() throws Exception {
        String username = "westssun";
        String jwtToken = this.joinAndLogin(username);

        mockMvc.perform(get("/user/load")
                .header(JwtEnum.HEADER_STRING.getValue(), jwtToken))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.username").value(username));
    }

    @DisplayName("로그인 유저 조회 실패 - 잘못된 토큰")
    @Test
    void getLoginUser_fail() throws Exception {
        String username = "westssun";
        String jwtToken = this.joinAndLogin(username) + "fail";

        mockMvc.perform(get("/user/load")
                        .header(JwtEnum.HEADER_STRING.getValue(), jwtToken))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
    }
}