package io.security.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 여러개가 있을때 Order 설정이 필요하다.
 * 2개의 설정 클래스가 있을때, 어떤 순서에 따라 적용할 것인가?
 */

/**
 * 스프링부트 구동
 *
 * 1) WebSecurity > FilterChainProxy 객체를 생성
 * (securityFilterChains 매개변수를 넘기는데, 여기엔 2개의 DefaultSecurityFilterChain 이 담겨져있다.)
 * (이 2개는 우리가 아래 설정한 2개의 securityConfig 이고, antMatcher 인 "/admin/**"과 any request 가 담겨져있다.)
 * (각 설정에 맞는 Filters 가 담겨져있다.)
 * 2) securityFilterChains 매개변수 : FilterChainProxy 생성자에 넘어간다는 것이다.
 *
 * url 호출
 *
 * 1) localhost:8080/
 * -> root 경로 : FilterChainProxy 에서 요청을 가장 먼저 받게된다.
 * -> filterChainProxy 는 2개의 DefaultSecurityFilterChain 을 가지고있는데, 이때 요청 url에 따라 찾아간다.
 *    / 경로 이므로 두번째에 해당한다.
 *
 * 2) localhost:8080/admin
 * -> 이번엔 첫번째 DefaultSecurityFilterChain 으로 간다.
 * -> 첫번째 설정 클래스에 해당하는 filters 가 적용된다.
 */
@Configuration
@EnableWebSecurity
@Order(0) // Order 의 순서를 잘 설정해야한다. MultiSecurityConfig2 가 먼저 타면, /admin/**도 두번째껄 타게된다.
public class MultiSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * /admin url 요청 시 아래로 작동
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic(); // 인증방식
    }
}

@Configuration
@Order(1)
class MultiSecurityConfig2 extends WebSecurityConfigurerAdapter {
    /**
     * /admin 외의 url 요청 시 아래로 작동
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll() // 모든 요청 허용
                .and()
                .formLogin(); // 인증방식
    }
}
