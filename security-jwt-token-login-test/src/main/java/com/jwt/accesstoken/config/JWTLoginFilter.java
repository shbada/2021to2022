package com.jwt.accesstoken.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.accesstoken.form.UserLoginForm;
import com.jwt.accesstoken.user.domain.SpUser;
import com.jwt.accesstoken.user.service.SpUserService;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * username password 를 줘서 유효한 사용자라는걸 증명하고,
 * 통과되면 인증토큰을 내려준다.
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private ObjectMapper objectMapper = new ObjectMapper();
    private SpUserService userService;

    public JWTLoginFilter(AuthenticationManager authenticationManager, SpUserService userService) {
        super(authenticationManager);
        // UsernamePasswordAuthenticationFilter 에서 잡아주는 request 경로
        setFilterProcessesUrl("/login"); // post login url 명시

        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginForm userLoginForm = objectMapper.readValue(request.getInputStream(), UserLoginForm.class);

        /* refresh token 이 없다면 */
        if (userLoginForm.getRefreshToken() == null) {
            // 인증 전이므로 authorities null
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userLoginForm.getUsername(), userLoginForm.getPassword(), null
            );

            // user details...
            /* AuthenticationManager 에게 토큰 검증을 요청하고, 그럼 AuthenticationManager 가 DAoAuthenticationProvider 가지고있는
               provider 를 통해서 userDetails (SPUser) 에서 가져와서 패스워드 검증 후 유저를 넣어준다
             */
            return getAuthenticationManager().authenticate(token);
        } else {
            /* refresh token 이 왔다면 */
            VerifyResult verify = JWTUtil.verify(userLoginForm.getRefreshToken());

            if (verify.isSuccess()) { // 유효하다면?
                // username 을 가지고 userService 에서 해당 유저 정보의 타당성 체크
                SpUser user = (SpUser) userService.loadUserByUsername(verify.getUsername());

                // userToken 생성 (수명연장)
                // auth_token, refresh_token 다시 갱신 (수명연장)

                return new UsernamePasswordAuthenticationToken(
                        user, user.getAuthorities() // user : principal
                );
            } else {
                throw new TokenExpiredException("refresh token expired");
            }
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // 인증 성공을 했다면 SpUser 를 가지고있을 것이다
        SpUser user = (SpUser) authResult.getPrincipal();

        // login 을 끝내려면 token 발행을 해줘야지 (Bearer token 규약)
        // auth token
        // response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + JWTUtil.makeAuthToken(user));
        response.setHeader("auth_token", JWTUtil.makeAuthToken(user));

        // refresh token 도 함께주자.
        response.setHeader("refresh_token", JWTUtil.makeRefreshToken(user));

        // user 정보를 내려준다
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(user));
    }
}
