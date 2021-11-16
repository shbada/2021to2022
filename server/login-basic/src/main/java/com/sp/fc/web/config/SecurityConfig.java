package com.sp.fc.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true) // ROLE 에 맞게 접근 가능하도록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 안되면 선언후 디버깅해보기
    //UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;

    private final CustomAuthDetails customAuthDetails;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(
                        User.withDefaultPasswordEncoder() // test 시에만 사용
                                .username("user1")
                                .password("1111")
                                .roles("USER")
                ).withUser(
                        User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("2222")
                                .roles("ADMIN")
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(request->{
                    request
                            .antMatchers("/").permitAll() // root page 허용
                            .anyRequest().authenticated() // 그 외는 권한을 받고 들어오라는 의미
                            //.antMatchers("/**").permitAll() // 모든 URL 에 대한 권한 허용
                            ;
                })
                .formLogin( // 로그인이 안되어있을 경우 로그인 페이지로 이동 (default security 로그인 페이지가 뜸)
                        // login page 지정해야 우리가 만든 login 페이지로 이동됨
                        login -> login.loginPage("/login")
                                .permitAll() // permitAll 을 해줘야 허용됨
                                .defaultSuccessUrl("/", false)
                        // false 로 해줘야 로그아웃 후 유저페이지 접근하려고하면 로그인 페이지로 이동되는데, 이때 로그인 후 유저페이지로 바로 갈수있게된다.
                                .failureUrl("/login-error") // 로그인 실패시 이동될 페이지
                                .authenticationDetailsSource(customAuthDetails) // /auth api 호출시 details 가 우리가 만든 형식에 맞게 보여준다.
                ) // 로그인을 특정해주지않으면 DefaultLoginPageGenerationFilter, DefaultLogoutPageGeneratingFilter 가 동시에 작동한다
                // 로그아웃을 하더라도 메인페이지에 머물도록 해보자.
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .exceptionHandling(exception -> exception.accessDeniedPage("/access-denied")) // 유저가 관리자 페이지로 이동하려고하면 해당 페이지로 이동시킴
        ;
    }

    // 관리자 -> 유저 권한 까지 허용시키기
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        // ADMIN 이 USER 가 할수 있는걸 다 할 수 있다.
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

        return roleHierarchy;
    }

    /**
     * 웹 리소스에 대한 권한 허용
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(
                        // js, css, image 등의 파일
                        PathRequest.toStaticResources().atCommonLocations()
                )
                ;
    }
}
