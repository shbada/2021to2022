package io.security.corespringsecurity.security.configs;

import io.security.corespringsecurity.security.common.FormWebAuthenticationDetailsSource;
import io.security.corespringsecurity.security.factory.UrlResourcesMapFactoryBean;
import io.security.corespringsecurity.security.filter.PermitAllFilter;
import io.security.corespringsecurity.security.handler.AjaxAuthenticationFailureHandler;
import io.security.corespringsecurity.security.handler.AjaxAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.handler.FormAccessDeniedHandler;
import io.security.corespringsecurity.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import io.security.corespringsecurity.security.provider.AjaxAuthenticationProvider;
import io.security.corespringsecurity.security.provider.FormAuthenticationProvider;
import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FormWebAuthenticationDetailsSource formWebAuthenticationDetailsSource;
    private final AuthenticationSuccessHandler formAuthenticationSuccessHandler;
    private final AuthenticationFailureHandler formAuthenticationFailureHandler;

    private final SecurityResourceService securityResourceService;
    private final String[] permitAllResources = {"/", "/login", "/user/login/**"};

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
        auth.authenticationProvider(ajaxAuthenticationProvider());
    }

    /**
     * 인증 관리자 설정
     * @return
     * @throws Exception
     */
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                /*
                    Authentication(user), FilterInvocation(request(/user)), List<ConfigAttribute>("hasRole('USER')")
                    위 3개 파라미터 정보가 필요하다.
                    이때 권한 정보는 requestMap 에서 꺼내서 셋팅된다. 이는 DefaultFilterInvocationSecurityMetadataSource 여기에 있다.

                    ExpressionBasedFilterInvocationSecurityMetadataSource.java

                    DefaultFilterInvocationSecurityMetadataSource.java
                    - private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

                    디버깅을 해보면?
                    1) FilterSecurityInterceptor.java
                    - FilterInvocation 객체를 생성한다. (/user : 요청정보를 담는다)

                    2) AbstractSecurityInterceptor.java (위 1)번의 부모클래스)
                    - beforeInvocation() 메서드 수행 - this.obtainSecurityMetadataSource().getAttributes(object) 호출로 아래 로직 수행됨

                    > security 초기화시, ExpressionBasedFilterInvocationSecurityMetadataSource.java 에서 권한 정보를 담은 map이 있음
                    > DefaultFilterInvocationSecurityMetadataSource 클래스에게 요청하면 requestMap을 리턴해준다.
                    > DefaultFilterInvocationSecurityMetadataSource 에서 getAttributes() 에서 요청 url에 맞는 권한을 리턴해주는 것이다.

                    > 이제 요청정보/권한정보가 있으므로, 인증정보가 필요하다.

                    3) AbstractSecurityInterceptor.java
                    - authenticateIfRequired() 메서드 수행
                    - 인증정보를 가져왔다.
                    - 이제 accessDecisionManager.decide(authenticated, object, attributes); 를 수행한다. (모든 파라미터의 정보는 채워져있다.)


                    (추가) urlFilterInvocationSecurityMetadataSource 로 설정했으므로 아래 3줄은 의미 없는 코드가 되어 주석 처리
                 */
//                .antMatchers("/mypage").hasRole("USER") /* USER 권한 접속 가능 */
//                .antMatchers("/messages").hasRole("MANAGER") /* MANAGER 권한 접속 가능 */
//                .antMatchers("/config").hasRole("ADMIN") /* ADMIN 권한 접속 가능 */
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .authenticationDetailsSource(formWebAuthenticationDetailsSource)
                .successHandler(formAuthenticationSuccessHandler)
                .failureHandler(formAuthenticationFailureHandler)
                .permitAll()
        .and()
                .exceptionHandling()
//                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .accessDeniedPage("/denied")
                .accessDeniedHandler(accessDeniedHandler())
        /*
            FilterSecurityInterceptor 보다 앞에 설정하여, 우리가 설정한 파일이 먼저 셋팅되어 수행
            FilterSecurityInterceptor.java > toFilter() 에서 수행될때 chain 파라미터에 우리가 만든 클래스가 있음
            - FilterChainProxy.java 를 보면 우리가 만든 FilterSecurityInterceptor 이 원래의 FilterSecurityInterceptor 보다 먼저있다.
            - 우리가 만든 FilterSecurityInterceptor엔 UrlFilterInvocationSecurityMetadataSource 로 설정되어있어서 우리껄 호출하게된다.
            AbstractSecurityInterceptor.java > this.obtainSecurityMetadataSource().getAttributes(object);
        */
        .and()
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class)
        ;

        http.csrf().disable();

        customConfigurer(http);
    }

    private void customConfigurer(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
                .loginProcessingUrl("/api/login")
                .setAuthenticationManager(authenticationManagerBean());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new FormAuthenticationProvider(passwordEncoder());
    }

    @Bean
    public AuthenticationProvider ajaxAuthenticationProvider(){
        return new AjaxAuthenticationProvider(passwordEncoder());
    }

    @Bean
    public AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler(){
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler(){
        return new AjaxAuthenticationFailureHandler();
    }

    public AccessDeniedHandler accessDeniedHandler() {
        FormAccessDeniedHandler commonAccessDeniedHandler = new FormAccessDeniedHandler();
        commonAccessDeniedHandler.setErrorPage("/denied");
        return commonAccessDeniedHandler;
    }

    /**
     * UrlFilterInvocationSecurityMetadataSource 설정
     * @return
     * @throws Exception
     */
//    @Bean
//    public FilterSecurityInterceptor customFilterSecurityInterceptor() throws Exception {
//
//        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
//        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
//        /*
//           - AffirmativeBased
//           - ConsensusBased
//           - UnanimousBased
//         */
//        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased()); // 접근 결정 관리자 설정 (3가지 타입이 있음)
//        /* 인증된 사용자인지 체크해야하기 때문에 인증관리자 설정 필요 */
//        filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean()); // 인증 관리자
//        return filterSecurityInterceptor;
//    }
    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {

        PermitAllFilter filterSecurityInterceptor = new PermitAllFilter(permitAllResources);
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        /*
           - AffirmativeBased
           - ConsensusBased
           - UnanimousBased
         */
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased()); // 접근 결정 관리자 설정 (3가지 타입이 있음)
        /* 인증된 사용자인지 체크해야하기 때문에 인증관리자 설정 필요 */
        filterSecurityInterceptor.setAuthenticationManager(authenticationManagerBean()); // 인증 관리자
        return filterSecurityInterceptor;
    }

    /**
     * 접근 결정 관리자 설정
     * @return
     */
    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecistionVoters());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<?>> getAccessDecistionVoters() {
        return Arrays.asList(new RoleVoter());
    }

    /**
     * urlResourcesMapFactoryBean().getObject() 을 추가해서 requestMap 에 데이터 넣어주기
     * @return
     */
    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), securityResourceService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean();
        urlResourcesMapFactoryBean.setSecurityResourceService(securityResourceService);

        return urlResourcesMapFactoryBean;
    }
}
