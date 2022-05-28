package io.security.corespringsecurity.security.config;

import io.security.corespringsecurity.security.common.FormAuthenticationDetailsSource;
import io.security.corespringsecurity.security.handler.CustomAccessDeniedHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.CustomAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final FormAuthenticationDetailsSource formAuthenticationDetailsSource;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    /**
     * 우리가 생성한 userDetailsService 를 사용하도록 셋팅
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
        // 우리가 만든 provider 사용
        auth.authenticationProvider(authenticationProvider());
    }

    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    /**
     * security 설정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // root 는 접속 가능하도록 한다.
                .antMatchers("/", "/users", "user/login/**", "/login*").permitAll()
                // Role 에 따른 처리
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()

                .formLogin()
        /* 로그인 페이지 커스텀 설정 */
                .loginPage("/login")
                .defaultSuccessUrl("/") // 로그인 성공시 home 으로
                .loginProcessingUrl("/login_proc") // login.html 에서 form action
                /*
                 * UsernamePasswordAuthenticationFilter
                 * 인증 시도시, usernamePasswordAuthenticationToken (authentication 객체) 를 만든다.
                 * setDetails() 호출
                 * -> 우리는 여기서 WebAuthenticationDetailSource 객체를 만들며 부가적인 기능을 수행한다.
                 * -> WebAuthenticationDetailSource -> WebAuthenticationDetails 객체를 생성
                 * 화면 : <input type="hidden" th:value="secret" name="secret_key" />
                 */
                /** formAuthenticationDetailsSource 커스텀 클래스 설정 */
                .authenticationDetailsSource(formAuthenticationDetailsSource)
                .successHandler(customAuthenticationSuccessHandler) // successHandler 설정
                .failureHandler(customAuthenticationFailureHandler) // failureHandler 설정
                .permitAll() // 로그인 화면은 권한 모두 허용

                .and()

                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                ;
    }

    private AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/denied");

        return accessDeniedHandler;
    }

    /**
     * 사용자 추가
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // password encode
//        String password = passwordEncoder().encode("1111");
//
//        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
////        auth.inMemoryAuthentication().withUser("admin").password(password).roles("MANAGER");
////        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
//        // 여러개의 ROLE을 줘야, ADMIN이 USER, MANAGER 이 접근 가능한 자원에 접근이 가능해진다.
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER", "USER");
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN", "USER", "MANAGER");
//    }

    /**
     * 보안 필터를 적용할 필요가 없는 리소스를 설정해야한다. (.js, .css, .image 등)
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // toStaticResources 에서 파일들에 대한 경로가 존재한다.
        /*
           .antMatchers("/").permitAll() 와 뭐가 다를까?
           - 공통점 : 인증/권한 설정을 무조건 통과한다.
           - .antMatchers("/").permitAll()는 보안 필터는 들어오고, 그 안에서 요청에 대해서 보안 통과가 되게끔 한다.
             결국 보안필터는 거친다는 것이다.
           - 그에비해 아래 ignoring 은 보안 필터 자체를 거치지 않는다.
        */
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 평문 패스워드를 암호화한다.
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}