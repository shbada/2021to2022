package com.example.userservice.security;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final UserService userService;
    private final Environment environment;


    /**
     * email, password token 변환
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        /** 로그인필터 1) 로그인 시도시, 제일 먼저 실행되는 함수 */
        try {
            RequestLogin creds = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(
                    /* 인자, 인자, 권한지정 */
                    new UsernamePasswordAuthenticationToken( /* token 형태로 변환 */
                            creds.getPassword(),
                            creds.getEmail(),
                            new ArrayList<>())
            );
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        /** 로그인필터 3) 로그인 성공시 호출되는 메소드 */
        String userName = ((User) authResult.getPrincipal()).getUsername(); // userEmail

        UserDto userDetails = userService.getUserDetailsByEmail(userName);
        String token = Jwts.builder()
                            .setSubject(userDetails.getUserId())
                            .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time")))) // 현재시간 기준 + 하루
                            .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                            .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());


        super.successfulAuthentication(request, response, chain, authResult);
    }


}
