package com.sp.fc.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicAuthenticationTest {

    @LocalServerPort
    int port;

    RestTemplate client = new RestTemplate();

    /* http */
    private String greetingUrl() {
        return "http://localhost:" + port + "/greeting";
    }

    @Test
    @DisplayName("인증 실패")
    public void test() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            client.getForObject(greetingUrl(), String.class);
        });

        assertEquals(401, exception.getRawStatusCode());
    }

    @DisplayName("2. 인증 성공")
    @Test
    void test_2() {
        // 헤더에 인증 정보 넣기
        // Basic 토큰이 넘어간다.
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64.getEncoder().encodeToString("user1:1111".getBytes()));

        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> resp = client.exchange(greetingUrl(), HttpMethod.GET, entity, String.class);

        assertEquals("hello", resp.getBody());
    }

    @DisplayName("인증성공 2")
    @Test
    void test_3() {
        // TestRestTemplate 는 기본적으로 basicToken 을 제공한다.
        TestRestTemplate testClient = new TestRestTemplate("user1", "1111");
        String resp = testClient.getForObject(greetingUrl(), String.class);

        assertEquals("hello", resp);
    }

    @DisplayName("POST 인증")
    @Test
    void test_4() {
        TestRestTemplate testClient = new TestRestTemplate("user1", "1111");

        // post
        ResponseEntity<String> resp = testClient.postForEntity(greetingUrl(), "west", String.class);

        // post 는 SecurityConfig.java 에 configure 메서드에 .csrf().disable() 를 추가해줘야 가능하다.
        // 근데 만약 enable 도 해야한다면?

        assertEquals("hello west", resp.getBody());
    }
}
