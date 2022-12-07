package io.security.basicsecurity.config.Order08_decision;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // WebSecurityConfiguration 등 여러 클래스들을 import 해준다.
public class DecisionSecurityConfig extends WebSecurityConfigurerAdapter {
    /*
      AccessDecisionManager
      > 여러개의 Voter 들을 받아서 이 Voter 들의 각각의 승인여부 체크
      - AffirmativeBased : 1개라도 승인이 있다면 승인
      - ConsensusBased : 다수의 개수에 따름 (동일할 경우 설정에 따라: )
      - UnanimousBased : 모두 승인이여야 승인

      AccessDecisionVoter
      - RoleVoter 등

      직접 구현도 가능하며, 유형을 관리할 수 있다.
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
