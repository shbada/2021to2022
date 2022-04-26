package io.security.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity // WebSecurityConfiguration 등 여러 클래스들을 import 해준다.
public class FormSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     UsernamePasswordAuthenticationFilter
     > UsernamePasswordAuthenticationToken 생성
     > getAuthenticationManager().authenticate(authRequest);
       - manager 에게 인증 관리 요청
       - ProviderManager.java > providers 를 리스트로 가지고있고, 이중 하나에 인증을 위임한다.
       - provider.authenticate(authentication); 호출
     > DaoAuthenticationProvider.java
       - getUserDetailsService().loadUserByUsername -> UserDetails 타입의 유저 객체 얻음
       - ID는 검증되었다고 여기까지 판단은 가능
       - 그다음 Password 검증 > 사용자가 입력한 비밀번호와 UserDetails 객체의 비밀번호 유효성 체크
       - 성공시 인증 정보를 다시금 인증 객체 UsernamePasswordAuthenticationToken 에 저장
       - Provider -> Manager 에게 리턴
       (인증실패시, 에러 발생)
     (인증성공)
     Manager 가 Provider 로부터 받은 result (UsernamePasswordAuthenticationToken) 를
     UsernamePasswordAuthenticationFilter 까지 최종적으로 리턴하게된다.

     최종적으로 Filter 에서 SecurityContextHolder.getContext().setAuthentication(authResult); 수행
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated();

        http
                .formLogin();
    }
}
