package io.security.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Configuration
//@EnableWebSecurity // WebSecurityConfiguration 등 여러 클래스들을 import 해준다.
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
    // debug
    // ExceptionTranslationFilter

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
                .antMatchers("/login").permitAll()
                // user 요청을 하면 권한 USER 인 유저인지 체크
                .antMatchers("/user").hasRole("USER")
                // /admin/** 이 admin/pay 보다 위에 있다면 /admin/pay 도 SYS 가 접근이 가능해져버린다.
                // 그러므로 위치를 잘 선정해야한다.
                .antMatchers("/admin/pay").hasRole("ADMIN")
                .antMatchers("/admin/**")
                    .access("hasRole('ADMIN') or hasRole('SYS')")
                .anyRequest().authenticated();

        /**
         * CSRF : default 로 설정되어있다.
         * 모든 요청에 랜덤하게 생성된 토큰을 HTTP 파라미터로 요구한다.
         * 요청시 전달되는 토큰 값과 서버에 저장된 실제 값과 비교한 후 일치하지 않으면 요청은 실패한다.
         *
         * 클라이언트가 최초 접속(요청)할때 서버에서 csrf token 을 발급해주고,
         * 클라이언트는 해당 토큰을 hidden 으로 가지고있어서 서버에 요청할때 함께 보내야한다.
         *
         * GET 방식 제외 호출시 csrf 가 비어져있으면 에러 발생한다.
         * CsrfFilter
         * 1) 여기서 서버에 저장된 토큰이 있는지 체크한다.
         * 2) 요청의 헤더&바디에 토큰을 설정하지 않았으므로 전송된 토큰은 null 이다.
         * 3) null 이기 때문에 서버에 저장된 토큰과 전송된 토큰 비교하는 로직에서 값이 false 가 된다.
         * 4) 그래서 InvalidCsrfTokenException 이 발생한다. (403)
         *
         * 서버에 있던 토큰을 header 에 넣어서 호출한다면? (POST)
         * 1) 위 3)번에서 서버에 저장된 토큰과 전송된 토큰 비교하는 로직에서 값이 true 가 나온다.
         * 2) 일치하므로 csrf 검사를 통과한다.
         */

        http
//                .csrf().disable() // csrf 비활성화 - CsrfFilter 가 아예 생성되지 않아서 사용되지않음
                .formLogin()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        // ExceptionTranslationFilter 에서 RequestCache 의 정보에 이미 세션이 저장되어있다.
                        // 그래서 정보를 꺼내와서 요청했었던 곳으로 보낸다.
                        RequestCache requestCache = new HttpSessionRequestCache();

                        // RequestCache 에 SavedRequest 를 실시간으로 담는다.
                        // RequestCacheAwareFilter.java > SavedRequest 가 존재하는지 체크하는 필터
                        // -> SavedRequest 객체가 존재하게 되면 그 객체가 null 이 아닐 경우 다음 필터로 객체를 넘겨주는 역할을 한다.
                        // -> savedRequest 객체를 계속해서 참조할 수 있도록한다.
                        SavedRequest savedRequest = requestCache.getRequest(request, response);

                        // request url
                        String redirectUrl = savedRequest.getRedirectUrl();
                        response.sendRedirect(redirectUrl);
                    }
                });

        http
                .exceptionHandling()
                /** ExceptionTranslationFilter */
                /* 인증 에러 */
                // 익명 사용자의 요청 또는 remember me 의 오류
                // -> ExceptionTranslationFilter > 인가 에러로 들어왔다가,
                // -> 인증을 받지 않은 익명사용자의 요청이므로,
                // -> 인증 예외때 처리하는 거기로 이동된다.  (sendStartAuthentication)
                // -> requestCache.saveRequest(request, response); // request, response 정보를 저장
                // -> HttpSessionRequestCache.java > saveRequest()
                // -> 요청 정보를 session 에 저장 (saveRequest())

                // -> authenticationEntryPoint.commence() 호출
                // -> 우리가 직접 만들었으므로 만든 메서드로 이동된다.
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                })
                /* 인가 에러 */
                /*
                 정상 로그인 후, ADMIN 권한으로 접근 가능한 곳에 USER 권한의 유저로 요청했다.
                 인가 에러가 발생한다.

                 -> AccessDeniedException
                 accessDeniedHandler > handel() 메서드가 호출된다.
                 */
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.sendRedirect("/denied");
                    }
                });
    }
}
