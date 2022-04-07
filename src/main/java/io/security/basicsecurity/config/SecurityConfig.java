package io.security.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@EnableWebSecurity // WebSecurityConfiguration 등 여러 클래스들을 import 해준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // debug
    /*
       FilterChainProxy > 이 필터가 가지고 있는 필터들 목록들이 많이 있다.
       nextFilter.doFilter() 시점에 additionalFilters 안의 순서대로 filter 를 수행시킨다.
       우리가 설정 클래스 (securityConfig > configure 에서 설정한 API 관련된 필터들이 위에 설정된다.)
     */
    // FilterChainProxy

    /*
       FilterChainProxy > 이 필터가 가지고 있는 필터들 목록들이 많이 있다.
       nextFilter.doFilter() 시점에 additionalFilters 안의 순서대로 filter 를 수행시킨다.
       우리가 설정 클래스 (securityConfig > configure 에서 설정한 API 관련된 필터들이 위에 설정된다.)

       UsernamePasswordAuthenticationFilter > attemptAuthentication()
       Provider (AuthenticationManager 의 구현체) : 인증 방식을 처리할 provider 을 찾는다. -> DaoAuthenticationProvider 선택됨
       UsernamePasswordAuthenticationToken 생성 > principal, authorities 등을 가지고있다. -> ProviderManger 로 반환 > UsernamePasswordAuthenticationFilter 로 반환
       인증 결과를 UsernamePasswordAuthenticationFilter 에서 SecurityContextHolder.getContext.setAuthentication(authResult)로 저장한다.
     */
    // UsernamePasswordAuthenticationFilter

    /*
       LogoutFilter > doFilter > 인증객체 꺼내옴 (UsernamePasswordAuthenticationToken)
       logoutHandlers - 여러개의 핸들러를 가지고있음
       - 이 중에 SecurityContextLogoutHandler 가 로그아웃 처리
     */
    // LogoutFilter

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // 요청에 대한 보안 체크
                // loginPage 로 이동해서 로그인을 수행해야한다.
                // loginPage 는 인증이 필요한 경로가 아니므로 제외시킨다.
                // 그래서 아래에 formLogin() 쪽 로직에 permitAll()을 추가했다.
                .anyRequest().authenticated();

        /** login */
        http.formLogin()
                // 인증을 해야한다면 여기로 이동한다.
                //.loginPage("/loginPage") // springSecurity 가 기본적으로 제공하는 로그인 페이지 아닌 직접 만든 화면 설정
                .defaultSuccessUrl("/") // 로그인 성공시 메인으로 이동
                .failureUrl("/login") // 실패시 여기로 이동 (invalid credentials 메시지 보임)
                .usernameParameter("userId") // default user (아이디 파라미터명 설정)
                .passwordParameter("passwd") // default password (패스워드 파라미터명 섲렁)
                .loginProcessingUrl("/login_proc") // 로그인 From Action URL
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // 인증에 성공한 유저명 출력
                        System.out.println("authentication " + authentication.getName());
                        response.sendRedirect("/"); // root 페이지로 이동
                    }
                }) // 로그인 성공시 핸들러 호출
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        // 인증 실패한 경우, 메시지 출력
                        System.out.println("exception " + exception.getMessage());
                        response.sendRedirect("/login");
                    }
                })
                .permitAll();
         // Form Login 방식으로 진행하겠다 는 의미

        /** logout */
        http
                .logout()
                .logoutUrl("/logout") // 기본적으로 post 방식이고 get 방식은 오류 발생 (따로 설정은 가능)
                .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동될 화면
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        session.invalidate(); // 세션 무효화
                    }
                })
                // logoutSuccessUrl 와 비슷한 개념인데, url 설정은 이동할 페이지 설정이고 핸들러는 더 다양한 로직을 수행시킬 수 있다.
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                })
                .deleteCookies("remember-me") // remember-me 인증시, remember-me 쿠키명으로 생성된 쿠키를 삭제함
                ;
    }
}
