package com.cos.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // IoC 빈(bean)을 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션 (스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.)
// prePostEnabled = true : preAuthorize, postAuthorize 어노테이션 활성화
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 패스워드 인코더 빈 등록
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); /* 비활성화 */
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll() /* 그외 요청들은 모두 허용 */
                .and()
                .formLogin()
                .loginPage("/loginForm") /* 우리가 사용할 login url */
                /* 화면에서 받아오는 input name 변경하고 싶을 경우 아래처럼 설정 가능 */
//                .usernameParameter("username2")
//                .passwordParameter("password2")
                .loginProcessingUrl("/loginProc") /* 해당 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행해준다. */
                .defaultSuccessUrl("/"); /* 로그인 성공시 이동될 페이지 */
    }
}
