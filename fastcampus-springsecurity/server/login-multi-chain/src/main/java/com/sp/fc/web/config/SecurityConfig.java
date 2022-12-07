package com.sp.fc.web.config;

import com.sp.fc.web.student.StudentManager;
import com.sp.fc.web.teacher.TeacherManager;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 하나의 필터체인 구성
 */

@Order(2)
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final StudentManager studentManager;
    private final TeacherManager teacherManager;

    public SecurityConfig(StudentManager studentManager, TeacherManager teacherManager) {
        this.studentManager = studentManager;
        this.teacherManager = teacherManager;
    }


    /**
     * authenticationProvider 설정
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // authenticationProvider 설정
        auth.authenticationProvider(studentManager);
        auth.authenticationProvider(teacherManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // formLogin 부분을 만들어서 사용
        CustomLoginFilter filter = new CustomLoginFilter(authenticationManager());

        http
                .authorizeRequests(request->
                        request.antMatchers("/", "/login").permitAll()
                                .anyRequest().authenticated()
                )
                // 추가 설정 해줘야 생략 가능
                .formLogin(
                        login->login.loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/", false)
                                .failureUrl("/login-error")
                )
                .addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .exceptionHandling(e -> e.accessDeniedPage("/access-denied"))
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        ;
    }
}
