package io.security.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity // WebSecurityConfiguration 등 여러 클래스들을 import 해준다.
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 사용자를 생성하고 권한을 설정할 수 있는 메서드
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 메모리 방식
        auth.inMemoryAuthentication()
                .withUser("user")
                // noop 은 평문으로 저장하겠다는 의미
                // passwordEncoder (암호화할때 prefix 를 함께 넣어야 match 할떄 어떤 유형의 알고리즘으로 할지 필요로한다.)
                .password("{noop}1111")
                .roles("USER");

        auth.inMemoryAuthentication()
                .withUser("sys")
                .password("{noop}1111")
                .roles("SYS");

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}1111")
                .roles("ADMIN");

        // 이렇게 권한을 모두 가지려면 ROLE 을 모두 선언해줘야하지만,
        // 추후에 권한 계층을 설정하여 ADMIN 을 가지고있으면 SYS, USER 해당하는 자원도 접근이 가능하도록 설정할 수 있다.
        auth.inMemoryAuthentication()
                .withUser("all")
                .password("{noop}1111")
                .roles("ADMIN", "SYS", "USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // user 요청을 하면 권한 USER 인 유저인지 체크
                .antMatchers("/user").hasRole("USER")
                // /admin/** 이 admin/pay 보다 위에 있다면 /admin/pay 도 SYS 가 접근이 가능해져버린다.
                // 그러므로 위치를 잘 선정해야한다.
                .antMatchers("/admin/pay").hasRole("ADMIN")
                .antMatchers("/admin/**")
                    .access("hasRole('ADMIN') or hasRole('SYS')")
                .anyRequest().authenticated();

        http
                .formLogin();
    }
}
