package com.jwt.accesstoken.config;

import com.jwt.accesstoken.user.service.SpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class AdvancedSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SpUserService userService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authenticationManager 로 위임 가능
        JWTLoginFilter loginFilter = new JWTLoginFilter(authenticationManager()); // authenticationManager 는 기본으로 만들어진다.
        // authenticationManager 위임 불가능 (사용자를 직접 가져올 상황이 생기기 때문) - spUserService 필요
        JWTCheckFilter jwtCheckFilter = new JWTCheckFilter(authenticationManager(), userService);

        http.csrf().disable() // token 사용을 위해 disable (계속 가지고 다니려면 비용이 많이든다)
                .sessionManagement(session ->
                        // session 사용하지 않겠다. 토큰을 쓰므로
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // loginFilter 를 UsernamePasswordAuthenticationFilter 대체시킨다.
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtCheckFilter, BasicAuthenticationFilter.class)
                ;
    }
}
