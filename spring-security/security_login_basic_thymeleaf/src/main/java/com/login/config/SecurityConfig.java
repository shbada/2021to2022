package com.login.config;

import com.login.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;
    private final AccountService accountService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/account/**", "/users/registerForm", "/users/register"
                              , "/users/signup", "/test/**").permitAll()
                .anyRequest().authenticated();

        /* 지정해줘야, UserDetailsService 구현한 클래스(AccountService)의 loadUserByUsername를 호출해준다. */
        http.formLogin()
                .loginPage("/login").permitAll();

        /* 로그인유지 remeberMe */
        http.rememberMe()
                .userDetailsService(accountService) /* userDetails */
                .tokenRepository(tokenRepository());
    }

    /**
     * rememberMe 를 위한 tokenRepository
     * 쿠키에 인증 정보를 남겨두고 세션이 만료 됐을 때에는 쿠키에 남아있는 정보로 인증한다.
     * @return
     */
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource); // (CREATE_TABLE_SQL 을 생성해줘야함) persistLogins.java 생성
        return jdbcTokenRepository;
    }

    /**
     * 정적 리소스 허용
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
