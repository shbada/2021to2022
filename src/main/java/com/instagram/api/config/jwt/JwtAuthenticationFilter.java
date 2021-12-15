package com.instagram.api.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.api.config.auth.PrincipalDetails;
import com.instagram.api.dto.UserLoginReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * formLogin.disable() 했기 때문에 아래 필터를 적용해야 localhost:8080/login 적용이 가능하다.
 *
 * extends UsernamePasswordAuthenticationFilter
 * /login 요청해서 username, password 전송하면 (post)
 * UsernamePasswordAuthenticationFilter 동작을 한다.
 *
 * securityConfig.java 에서 JwtAuthenticationFilter 를 필터로 등록했다.
 * 필수 파라미터 : authenticationManager
 * authenticationManager 을 통해서 로그인이 진행되기 떄문
 *
 *
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 필수 파라미터 (주입)
    private final AuthenticationManager authenticationManager;

    /**
     * /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
     * Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
     * 인증 요청시에 실행되는 함수 => /login
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        System.out.println("JwtAuthenticationFilter : 진입");

        // 1. username, password 를 받아서
        // 2. 정상인지 로그인 시도를 해본다. authenticationManager 로 로그인 시도를 하면
        //    PrincipalDetailsService 가 호출이 된다!(loadUserbyUsername 메서드가 실행됨)
        // 3. PrincipalDetails 를 세션에 담고 (권한 관리를 위해)
        // 4. JWT 토큰을 만들어서 응답해준다.

        // request에 있는 username과 password를 파싱해서 자바 Object로 받기
        ObjectMapper om = new ObjectMapper();
        UserLoginReqDto userLoginReqDto = null;

        // 1. username, password 를 받아서
        try {
            userLoginReqDto = om.readValue(request.getInputStream(), UserLoginReqDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("JwtAuthenticationFilter : "+ userLoginReqDto);

        // 유저네임패스워드 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginReqDto.getUsername(),
                        userLoginReqDto.getPassword()); // 패스워드 관련은 스프링이 해준다.

        System.out.println("JwtAuthenticationFilter : 토큰생성완료");

        // authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
        // loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고 : username
        // UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
        // UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
        // Authentication 객체를 만들어서 필터체인으로 리턴해준다.

        // Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
        // Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
        // 결론은 인증 프로바이더에게 알려줄 필요가 없음.
        // 2. 정상인지 로그인 시도를 해본다.
        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();

        // 정상 출력되면 로그인이 정상적으로 되었다는 뜻
        System.out.println("Authentication : "+principalDetailis.getUser().getUsername());

        // authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return 해주면 된다.
        // 리턴의 이유는? 권한 관리를 security가 대신 해주기 때문에 편하려고 하는것
        // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없다. 근데 단지, 권한 처리 때문에 세션에 넣어준다.
        return authentication;
    }

    /**
     * attemptAuthentication 이 실행 후 인증이 정상적으로 되었으면
     * successfulAuthentication 함수가 실행된다.
     * JWT Token 생성해서 request 요청한 사용자에게 JWT 토큰을 response에 담아주기
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        /* get principalDetails */
        PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();

        /* JWT 토큰 생성 (Hash 암호 방식, HMAC512: key 값으로 암호화) */
        String jwtToken = JWT.create()
                .withSubject(principalDetailis.getUsername())
                /* 만료시간 */
                .withExpiresAt(new Date(System.currentTimeMillis() + Integer.parseInt(JwtEnum.EXPIRATION_TIME.getValue())))
                .withClaim("id", principalDetailis.getUser().getId())
                .withClaim("username", principalDetailis.getUser().getUsername())
                .sign(Algorithm.HMAC512(JwtEnum.SECRET.getValue()));

        // 클라이언트는 이제 서버에 요청을 할때마다 아래 토큰을 함께 보내야한다.
        response.addHeader(JwtEnum.HEADER_STRING.getValue(), JwtEnum.TOKEN_PREFIX.getValue() + jwtToken);
    }

}
