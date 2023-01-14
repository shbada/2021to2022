package com.example.userservice.security;

import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration /* 메모리 빈으로 등록 */
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment environment;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        /* 모든 요청에 대해 허용 */
        //http.authorizeRequests().antMatchers("/users/**").permitAll();

        /* 요청 관리 */
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests().antMatchers("/**") // 모든 요청에 대한 미허용
                            .hasIpAddress("192.168.0.8") // IP 허용
                            .and()
                            .addFilter(getAuthenticationFilter()); // filter 를 통과한 데이터만 허용 (filter 등록)
        /* h2.console URL 에러 발생 방지 */
        http.headers().frameOptions().disable();
    }


    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment);
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // select pwd from users where email = ?
        // db_pwd(encrypted) == input_pwd(encrypted)
        /* 사용자 검색 -> password encoder */
        // userService 에 상속 추가 (extends UserDetailsService)
        /* loadUserByUsername() 메소드 */
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}
