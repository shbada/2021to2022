package com.westssun.designpatterns._03_behavioral_patterns._13_chain_of_responsibilities._03_java;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.ServletRequestPathFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 하나의 필터체인을 설정하는 코드다.
        // spring security 는 거대한 필터체인
        // 여러 Filter 들을 거치면서 각 책임에 맞는 원칙을 체크한다.
        http.authorizeRequests().anyRequest().permitAll().and();
//                .addFilter(new ServletRequestPathFilter())
//                .addFilter(new ServletRequestPathFilter());
    }

}
