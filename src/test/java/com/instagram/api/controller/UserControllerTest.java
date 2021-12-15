package com.instagram.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.api.SpIntegrationTest;
import com.instagram.api.config.jwt.JwtEnum;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.dto.UserLoginReqDto;
import com.instagram.api.repository.UserRepository;
import com.instagram.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest extends SpIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * 회원가입 및 로그인 실행
     * @param username
     * @return
     * @throws URISyntaxException
     */
    String joinAndLogin(String username) throws URISyntaxException {
        /* 1) 회원가입 */
        UserJoinReqDto userJoinReqDto = UserJoinReqDto.builder()
                .username(username)
                .password("123123")
                .email("test@test.com")
                .name("ksh")
                .build();
        userService.addUser(userJoinReqDto);

        /* 2) 로그인 */
        UserLoginReqDto userLoginReqDto = UserLoginReqDto.builder()
                .username(username)
                .password("123123")
                .build();

        HttpEntity<UserLoginReqDto> body = new HttpEntity<>(userLoginReqDto);
        ResponseEntity<String> response = restTemplate.exchange(uri("/login"),
                HttpMethod.POST, body, String.class);

        return response.getHeaders().get(JwtEnum.HEADER_STRING.getValue()).get(0);
    }

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