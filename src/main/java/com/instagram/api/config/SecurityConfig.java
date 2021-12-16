package com.instagram.api.config;

import com.instagram.api.common.exception.ExceptionHandlerFilter;
import com.instagram.api.config.jwt.JwtAuthenticationFilter;
import com.instagram.api.config.jwt.JwtAuthorizationFilter;
import com.instagram.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    /**
     * 패스워드 인코더 빈 등록
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    /**
     * configure
     * exceptionHandlerFilter : 기본 필터 체인 및 순서를 고려하여 일찍 타도록 선언하는게 좋다. 우선, LogoutFilter 앞에 순서 지정한다.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /* filter exception handler set */
            .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class)
            /* cors set */
            .addFilter(corsConfig.corsFilter())
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
            .authorizeRequests()
            .mvcMatchers("/test/**", "/user/join").permitAll() /* test API, 회원가입 - 권한 허용*/
            .antMatchers("/post/**")
            .access("hasRole('ROLE_USER')") /* 권한 - 유저 */
            .antMatchers("/user/**")
            .access("hasRole('ROLE_USER')") /* 권한 - 유저 */
            .anyRequest().authenticated();
    }
}
