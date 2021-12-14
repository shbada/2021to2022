package com.cos.jwt.config;

import com.cos.jwt.config.jwt.JwtAuthenticationFilter;
import com.cos.jwt.config.jwt.JwtAuthorizationFilter;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;

    /**
     * 스프링이 아래 빈을 찾아서 자동으로 사용함
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilter(corsConfig.corsFilter()) /* cors set (@CrossOrigin(인증이 없을떄만 가능하므로) 시큐리티에 설정 필요 */
                .csrf().disable()
                /* 세션을 사용하지 않겠다는 의미 */
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable() /* 사용안함 localhost:8080/login 사용 불가 (별도 필터 적용 필요) */
                /**
                 ID, PW 로 로그인 시도를 하면 (최초)
                 서버는 세션 메모리 영역에 세션ID를 만들고, 그 홍길동만의 영역을 만들어준다.
                 그리고 응답은 세션에 user Object를 저장하고 이 세션 ID를 리턴해준다.
                 클라이언트는 웹 브라우저로 요청했으므로, 웹 브라우저가 자기의 쿠키 영역에 세션 ID를 저장한다.
                 그 후 다음 요청부터는 세션 ID를 들고다니며, 서버는 세션 ID를 통해서 검증한다. (인증)
                 ---> 세션 방식

                 위 방식의 단점은 서버가 여러대가 생기면 불편하다. 서버마다 세션 영역이 별도로 있기 때문.
                 ajax 같은걸 쓰면 클라이언트가 javascript로 요청하게되는데,
                 이때 쿠키는 동일 도메인에서만 가능

                 http basic 인증 방식
                 headers 에 Authorization (ID, PW)를 담아서 인증되는 방식
                 단점) http 는 ID, PW 가 암호화가 안되서 노출 가능
                      이를 막기위해 https 를 쓰면 ID, PW 가 암호화된다.

                 Authorization (토큰) Bearer >유효시간을 가짐.
                 : 노출이 안되야 하지만, 노출이 된다해도 가지고있는 정보가 중요한 개인정보가 아니라면 위험부담은 조금은 적다.
                 */
                .httpBasic().disable() /* 사용안함 */

                // authenticationManager는 WebSecurityConfigurerAdapter 가 들고있어서 아래로 사용 가능
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))

                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

    }
}
