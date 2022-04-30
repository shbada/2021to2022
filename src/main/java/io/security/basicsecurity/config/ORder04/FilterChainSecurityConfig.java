package io.security.basicsecurity.config.ORder04;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 어떤 요청이 있다.
 * 이 요청이 실제로 Servlet 으로 가서, Servlet 에서 요청을 받아서 처리한다.
 * Filter -> Servlet
 * Servlet 자원에서 요청 처리가 완료되면, Filter 를 거쳐서 클라이언트에게 결과를 내려준다.
 *
 * 요청에 대한 최종적인 자원의 접근 전/후에 filter 가 어떤 처리를 할 수 있도록 filter 을 사용한다.
 *
 * Servlet filter 는 servlet 컨테이너에서 수행되어야한다.
 * 그래서 Spring Bean 을 Servlet Filter 에서 사용할 수 없다.
 * SpringBean (Spring 컨테이너), Servlet Filter (Servlet 컨테이너) 서로 다른 곳에 있기 때문이다.
 *
 * SpringSecurity 는 사용자의 모든 요청에 대해서 Filter 기반으로 처리하고 있다.
 * Filter 기반으로 모든 요청/인가 처리를 하고있는데, 이 Filter 는 Spring Bean 을 Injection 할 수 없다.
 * Filter 에서도 Spring bean 을 사용하고자하는 요구사항이 생겼다.
 * 그래서 Spring Security 에서는 Filter 기반으로 보안처리를 하고, 그 Filter 는 스프링 빈을 사용해야 한다.
 * 그래서 Spring Security 는 Spring Bean 을 만들고 Servet Filter 를 구현한다.
 * 그러면 Bean 으로 생성된 Spring Bean 객체는 Filter 타입을 갖게되고,
 * 사용자가 요청하게되면 Servlet 기반으로 동작하기 때문에 Servlet filter 로 가게된다. (Spring bean 이 직접 받지 못한다.)
 * Spring Bean 이 Filter 를 구현했다 할지라도 직접 받지 못한다.
 * WAS 에서 실행된 Servlet Filter 가 먼저 받게된다.
 *
 * 그래서 Servlet Filter와 Spring 에서 구현한 Spring Bean - Servlet Filter 사이에서 의 관계
 * : 사용자의 요청을 Spring Bean 이 전달받고, Spring Bean 에서 처리하고, 이 Bean 은 Filter 도 구현되어있다.
 * : 이런 요구 사항을 만족시키기 위해서 DelegatingFilterProxy 가 존재한다.
 *
 * [DelegatingFilterProxy]
 * : Servlet Filter 로, 스프링에서 관리하는 filter 가 아니다.
 * : Servlet FIlter 로 들어온 요청을 Spring Bean 에게 위임하는 역할을 한다.
 *   (요청을 스프링 컨테이너에 생성 된 Filter를 구현한 스프링 빈에 위임한다)
 * : Spring Security 는 이로써 Filter 기반으로 보안처리를 하고, Spring 의 기술도 사용할 수 있다.
 * [DelegatingFilterProxy 역할]
 * : Spring Security 에서 springSecurityFilterChain 이름으로 생성된 빈을 ApplicationContext 에서 찾아 요청을 위임한다.
 * : 실제 보안 처리를 하지 않는다.
 *
 * [springSecurityFilterChain]
 * [01. FilterChainProxy]
 * - springSecurityFilterChain 의 이름으로 생성되는 필터 빈
 * - 실제 보안 처리
 * - 스프링 시큐리티 초기화시 생성되는 필터들을 관리/제어
 * - 사용자의 요청을 필터 순서대로 호출하여 전달
 */
//@Configuration
//@EnableWebSecurity
public class FilterChainSecurityConfig extends WebSecurityConfigurerAdapter {
    // 스프링 부트 기동시, 스프링 시큐리티 초기화
    /*
      WebSecurity > 빈을 생성하는데, 여기서 생성되는게 FilterChainProxy 다.

      1) SecurityFilterAutoConfiguration 클래스에서 DelegatingFilterProxyRegistrationBean를 빈으로 등록한다.
      2) DelegatingFilterProxyRegistrationBean 클래스 DelegatingFilterProxy 객체를 생성한다.
      3) DelegatingFilterProxy로 요청이 들어올 때 처리를 위임 할 스프링 빈 이름(springSecurityFilterChain)을 설정한다.
      4) 요청이 들어오면 가장 먼저 DelegatingFilterProxy의 doFilter() 함수가 호출된다.
      5) doFilter() 함수는 요청을 위임할 필터(springSecurityFilterChain)를 찾아서 해당 요청을 위임(invokeDelete())한다.
      6) invokeDelete() 함수 호출 시 내부에서 FilterChainProxy의 doFilter() 함수를 호출하고,
         doFilterInternal() 함수에서는 등록 된 Filter 목록을 가지고 인증/인가 처리를 진행한다.
     */
    // DelegatingFilterProxy

    /*
      webSecurity.build() 함수 호출 시 springSecurityFilterChain 이름의 FilterChainProxy 빈을 생성한다.
     */
    // FilterChainProxy
}
