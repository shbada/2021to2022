package com.instagram.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.api.config.jwt.JwtEnum;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.dto.UserLoginReqDto;
import com.instagram.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;

public class IntegrationTest {
    /**
     * 내장 서버 랜덤 포트로 띄었을때 port 를 알 수 없으므로 이렇게 처리한다.
     */
    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    protected RestTemplate restTemplate = new RestTemplate();

    protected URI uri(String path) throws URISyntaxException {
        return new URI(format("http://localhost:%d%s", port, path));
    }

    /**
     * 회원가입 및 로그인 실행
     * @param username
     * @return
     * @throws URISyntaxException
     */
    protected String joinAndLogin(String username) throws URISyntaxException {
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
}
