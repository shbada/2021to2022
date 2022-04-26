package io.security.basicsecurity.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityContextPersistenceFilterConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * SecurityContextPersistenceFilter
         * 지금 사용자가 처음 접속하는 경우, securityContext 신규 생성하여 저장하는 역할을 하고,
         * 인증을 이미 받은 사용자라면 session 에 securityContext 를 이미 저장했기 때문에
         * securityContext 안에는 인증 객체가 이미 있으므로 securityContextHolder 에 담아서
         * 다음 필터로 이동하는 역할을 한다.
         * 그래서 계속적으로 인증을 받은 사용자의 요청이면 계속해서 securityContextHolder 에서
         * securityContext 를 꺼내서 인증 객체를 참조할 수 있다.
         *
         *
         * 1) 익명 사용자의 경우
         * HttpSessionSecurityContextRepository
         * > Session 에 SecurityContext 객체가 있으면 가져온다.
         * ThreadLocal 생성하여 SecurityContext 신규 생성하여 저장
         * SecurityContextHolder 에 저장
         * finally 에서 여러 처리 수행
         * - AnonymousAuthenticationFilter - AnonymousAuthenticationToken 에 저장
         * - securityContextHolder.clearContext();
         *
         * 2) 인증
         * session 에는 여전히 null
         * SecurityContextHolder.getContext().setAuthentication(authResult);
         * -> 인증 객체를 저장
         *
         * httpSession 에 securityContext 를 저장
         * 마지막으로 securityContextHolder.clearContext();
         */
        http
                .authorizeRequests()
                .anyRequest().permitAll() // 모든 요청 허용
                .and()
                .formLogin(); // 인증방식
    }
}
