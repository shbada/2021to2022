package io.security.corespringsecurity.security.common;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * FormWebAuthenticationDetails 을 생성하는 클래스
 *
 * [화면에서 request  - 로그인 수행]
 * 1) UsernamePasswordAuthenticationFilter 로 와서, setDetails()가 호출되었다.
 * - FormAuthenticationDetailsSource (securityConfig 에서 설정하여 여기를 찾는다.)
 * 2) FormAuthenticationDetailsSource.buildDetails() 가 실행된다.
 * 3) FormAuthenticationDetails 의 생성자가 수행되어 secret_key 가 저장된다.
 * 4) 그렇게 처리 후, CustomAuthenticationProvider의 authenticate()가 수행된다.
 */
@Component // bean
public class FormAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
        return new FormWebAuthenticationDetails(context);
    }
}
