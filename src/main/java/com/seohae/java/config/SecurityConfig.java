package com.seohae.java.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity /* 기본 스프링부트 설정이 아닌 내가 직접 설정하겠다는 의미 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                /* 아래 URL 들은 인증 없이 패스 */
                .mvcMatchers("/").permitAll()
                /* GET 메서드 중에서 /profile/* 경로는 인증 없이 패스 */
                .mvcMatchers(HttpMethod.GET, "/member/*").permitAll()
                .mvcMatchers("/v2/api-docs").permitAll()
                .mvcMatchers("/configuration/ui").permitAll()
                .mvcMatchers("/swagger-resources/**").permitAll()
                .mvcMatchers("/configuration/security").permitAll()
                .mvcMatchers("/swagger-ui.html").permitAll()
                .mvcMatchers("/swagger-ui/*").permitAll()
                .mvcMatchers("/webjars/**").permitAll()
                .mvcMatchers("/v2/**").permitAll()
                .anyRequest().authenticated(); /* 로그인 해야만 사용 가능 */
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        /* resource 관련 필터 적용 하지 않기 */
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .antMatchers("/h2-console/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
