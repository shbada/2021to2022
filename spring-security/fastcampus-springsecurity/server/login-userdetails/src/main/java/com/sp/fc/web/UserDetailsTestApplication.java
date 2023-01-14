package com.sp.fc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * UsernamePasswordAuthenticationFilter 는 UsernamePasswordAuthenticationToken을 가지고 ProviderManager 로 넘겨주고,
 * 기본적으로는 DaoAuthenticationProvider 가 이 토큰을 처리해준다.
 * 이때 UserDetailsService 빈이 있으면 DaoAuthenticationProvider 가 이 빈에 UsernamePasswordAuthenticationToken 토큰을 넘겨서
 * UserDetails 라는 Principal 객체를 받도록 되어있고,
 * 이 객체를 가지고 UsernamePasswordAuthenticationToken 토큰에 인증된 사용자라면 넣어서 리턴을 하도록 구조화되어있다.
 */
@SpringBootApplication(scanBasePackages = {
        "com.sp.fc.user",
        "com.sp.fc.web"
})
@EntityScan(basePackages = {
        "com.sp.fc.user.domain"
})
@EnableJpaRepositories(basePackages = {
        "com.sp.fc.user.repository"
})
public class UserDetailsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDetailsTestApplication.class, args);
    }

}
