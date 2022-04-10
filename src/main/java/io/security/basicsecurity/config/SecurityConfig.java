package io.security.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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

    /*
       authentication 이 null 일경우 / null이 아닐 경우 처리
       로그인 안한 상태로 루트 화면 접근시,
       securityContextHolder.getContext().getAuthentication() : null 이다.

       createAuthentication() : ROLE_ANONYMOUS 권한으로 익명 객체를 생성하여 securityContext 안에 저장한다.

       그 이후,
       AbstractSecurityInterceptor.java 에서 최종적으로 접근이 가능한지 인가처리를 한다.
       여기서 securityContextHolder.getContext().getAuthentication() 가 null 이면 에러를 발생시킨다.
       그러므로 인증 처리를 하지 않았어도 '익명 사용자용 인증 토큰'을 만들어서 설정되었다면 에러가 발생하지 않는다.

       decide() : 권한 체크

       -> 익명 사용자이면 에러가 발생하지만 AccessDeniedException 예외를 잡은 로직에서 isAnonymous 값을 설정한다.
       AuthenticationTrustResolverImpl.java 에서 isAnonymous 값을 리턴한다.
     */
    // AnonymousAuthenticationFilter

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

                /** remember Me */
                .and()

                /*
                   [로그인 완료시]
                   스프링 시큐리티에서 사용자의 세션이 생성되었고, 그 세션이 성공한 인증 객체를 담고있다.
                   서버가 인증을 성공한 그 사용자에게 세션을 생성할때 가지고있는 세션 아이디를 같이 보내고 응답 헤더에 실어서 보내고,
                   클라이언트가 JSESSIONID 를 가지고있을 것이다.
                   그 상태에서 클라이언트가 다시 서버에 접속하게 되면 인증을 안받아도 된다.
                   위에서 받은 JSESSIONID 를 가지고 서버에 요청하고, 서버가 이 ID와 매칭되는 인증 정보를 꺼내기 때문이다.
                   security Context 안에 인증객체가 있고, 이 정보를 계속적으로 가지고 이 사용자가 인증된 사용자인지 판단한다.

                   클라이언트에서 JSESSIONID 를 삭제하고 루트 페이지로 이동하면, 다시 로그인을 해야한다. (기존의 세션을 찾지 못하므로)

                   Remember-me 체크하고 로그인 후, JSESSIONID 안에 remember-me 이름으로 쿠키를 생성했는데,
                   기본 JSESSIONID 를 삭제하고 루트 페이지로 가도 인증 정보가 유지된다.
                   remember-me 의 value 에는 유저 아이디, 패스워드, 만료일이 들어있다.

                   JSESSIONID 가 없다 하더라도, remember-me 쿠키명을 가지고 왔으면 이걸 확인하고 해당 쿠키 값으로
                   유저 아이디/패스워드를 확인해서 다시 인증 정보를 생성한다.
                 */

                /*
                   rememberMeAuthenticationFilter
                   사용자가 실시간 타임아웃 또는 해당 사용하고있는 세션이 끊긴 경우거나 등 인증 객체를 시큐리티 컨텍스트 안에서
                   찾지 못하는 경우, 자동적으로 그 사용자의 인증을 유지하기 위해서 이 필터가 인증을 시도한다.
                   다시 인증 객체를 사용자에게 전달해준다. 인증이 유지될 수 있도록한다.

                   1) 인증 객체가 없는 경우
                   2) remember-me 쿠키가 있는 경우

                   위 2가지 경우를 만족했을때 위 필터가 작동한다.

                   TokenBaseRememberMeServices : 만료기간 방식
                   PersistentTokenBasedRememberMeServices : 영구적인 방식 (DB에 토큰 저장)

                   - 세션이 만료되었더라도 인증을 유지할 수 있다.
                 */
                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3600) // 1시간
                .userDetailsService(userDetailsService) // 유저 정보를 조회하는 기능을 위한 클래스 설정
                ;
    }
}
