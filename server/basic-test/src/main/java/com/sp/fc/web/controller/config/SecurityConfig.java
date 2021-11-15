package com.sp.fc.web.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true) // request 가 올때마다 어떤 filter 를 타게되는지 로그가 남는다.
// 컨트롤러의 @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") 가 이제 적용된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // prePostEnabled 로 권한체크를 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter : filterChain 구성
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.builder()
                .username("user2")
                .password(passwordEncoder().encode("2222")) // 인코딩 안하면 에러난다.
                .roles("USER" /* USER */
                )).withUser(User.builder()
                .username("admin")
                .password(passwordEncoder().encode("3333"))
                .roles("ADMIN")) /* ADMIN */
        ;

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 스프링 시큐리티는 기본적으로 모든 페이지를 막는다.
     * 기본 웹페이지 접근은 허용해야한다.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 여기서 설정하는 것이 filterChain 설정
        http.authorizeRequests((requests) ->
                requests.antMatchers("/").permitAll() // localhost:9050/ 는 접근 가능, /**는 로그인필요
                        .anyRequest().authenticated());

        http.formLogin();
        http.httpBasic();
    }
}
