package io.security.basicsecurity.config.Order07;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // WebSecurityConfiguration 등 여러 클래스들을 import 해준다.
public class AuthorizationSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
      FilterSecurityInterceptor (부모 : AbstractSecurityInterceptor)
      AffirmativeBased

      오류 발생시 - ExceptionTranslationFilter


     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated();

        http
                .formLogin();
    }
}
