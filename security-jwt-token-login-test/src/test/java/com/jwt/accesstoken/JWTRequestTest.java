package com.jwt.accesstoken;

import com.jwt.accesstoken.form.UserLoginForm;
import com.jwt.accesstoken.user.domain.SpUser;
import com.jwt.accesstoken.user.repository.SpUserRepository;
import com.jwt.accesstoken.user.service.SpUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JWTRequestTest extends WebIntegrationTest {
    @Autowired
    private SpUserRepository userRepository;

    @Autowired
    private SpUserService userService;

    @BeforeEach
    void before() {
        userRepository.deleteAll();

        /* user 등록 */
        SpUser user = userService.save(SpUser.builder()
                .email("user1")
                .password("1111")
                .enabled(true)
                .build());

        /* 권한 부여 */
        userService.addAuthority(user.getUserId(), "ROLE_USER");
    }

    private TokenBox getToken() {
        RestTemplate client = new RestTemplate();

        HttpEntity<UserLoginForm> body = new HttpEntity<>(
                UserLoginForm.builder()
                        .username("user1")
                        .password("1111")
                        .build()
        );

        /* post login call */
        // server 에서 정당한 유저라면 SpUser 를 내려준다
        ResponseEntity<SpUser> resp1 = client.exchange(uri("/login"), HttpMethod.POST, body, SpUser.class);

        /**
         * 1) 로그인을 통해서 유저가 token 을 받아오려고 한다. LoginFilter 에 걸렸다.
         * 2) post login 이 왔기 때문에 LoginFilter 의 attempAuthentication() 가 수행됨
         * 3) 로그인 유저 데이터를 넘겼기 때문에 파싱을 해서 데이터를 받아내고, 인증받지 않은 통행증(토큰)을 만든다.
         * 4) 인증되지않은 통행증이 AuthenticationManager 에게 넘긴다.
         * 5) AuthenticationManager 가 DaoAuthenticationProvider 를 통해서 UserDetailsService 에게 인증해달라고 요청
         * 6) UserDetailsService 구현한 SpUserService의 loadUserByUsername 를 통해 검증이 된다.
         * 7) 검증이 되면 LoginFilter 의 successfulAuthentication() 실행되어 해당 메서드는 UserPrincipal(=SpUser/UserDetails) 을 받아서
         *    token 을 헤더에 만들어줬음 (Bearer xx)
         * 8) User 값을 json 으로 담아서 response 한다.
         */
        System.out.println(resp1.getHeaders().get("auth_token").get(0));
        System.out.println(resp1.getHeaders().get("refresh_token").get(0));
        // SpUser(userId=1, email=user1, password=1111, authorities=[SpAuthority(userId=1, authority=ROLE_USER)], enabled=true)
        System.out.println(resp1.getBody());

        return TokenBox.builder()
                .authToken(resp1.getHeaders().get("auth_token").get(0))
                .refreshToken(resp1.getHeaders().get("refresh_token").get(0))
                .build();
    }

    /**
     * refresh token
     * @param refreshToken
     * @return
     */
    private TokenBox refreshToken(String refreshToken) {
        RestTemplate client = new RestTemplate();

        // auth token 이 아닌 refresh token 을 보내면 서버가 token 을 갱신하고 다시 내려준다.
        // 이를 token 으로 받아서 우리가 사용이 다시 가능하다.
        HttpEntity<UserLoginForm> body = new HttpEntity<>(
                UserLoginForm.builder().refreshToken(refreshToken).build()
        );

        ResponseEntity<SpUser> resp1 = client.exchange(uri("/login"), HttpMethod.POST, body, SpUser.class);

        return TokenBox.builder()
                .authToken(resp1.getHeaders().get("auth_token").get(0))
                .refreshToken(resp1.getHeaders().get("refresh_token").get(0))
                .build();
    }

    @DisplayName("1. hello 메시지를 받아온다... ")
    @Test
    void test_1() {
        String token = this.getToken().getAuthToken();

        RestTemplate client = new RestTemplate();

        // 이제 인증토큰을 1개 받은거고,
        // 이제 greeting 메서드를 호출해보자
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        // header 에 Authorization 를 넣었고,
        // token 이 함께 요청되고, JWTCheckFilter 에서 doFilterInternal 이 호출된다.
        // 여기서 토큰의 유효성을 체크
        HttpEntity body = new HttpEntity<>(null, header); // header 값을 전달해줘야한다.
        ResponseEntity<String> resp2 = client.exchange(uri("/greeting"), HttpMethod.GET, body, String.class);

        // 403 error 발생 (인증정보가 없으므로 :stateless 이기 때문) - JWTCheckFilter 를 타게 되어있음.
        // JWTCheckFilter : doFilterInternal 수정 후 pass 확인 완료

        System.out.println(resp2.getBody());
        assertEquals("hello", resp2.getBody());
    }

    @DisplayName("2. 토큰 만료 테스트 ")
    @Test
    void test_2() throws InterruptedException {
        /* get token */
        TokenBox tokenBox = this.getToken();

        RestTemplate client = new RestTemplate();

        /** 만료 상황 만들기 */
        Thread.sleep(3000); // Token is not valid

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenBox.getAuthToken());

        assertThrows(Exception.class, ()->{
            HttpEntity body = new HttpEntity<>(null, header);
            ResponseEntity<String> resp2 = client.exchange(uri("/greeting"), HttpMethod.GET, body, String.class);
        });

        HttpHeaders header2 = new HttpHeaders();
        tokenBox = this.refreshToken(tokenBox.getRefreshToken());

        // 다시 받아온 token
        header2.add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenBox.getAuthToken());
        HttpEntity body = new HttpEntity<>(null, header2);

        ResponseEntity<String> resp3 = client.exchange(uri("/greeting"), HttpMethod.GET, body, String.class);

        System.out.println(resp3.getBody());
        assertEquals("hello", resp3.getBody());
    }
}
