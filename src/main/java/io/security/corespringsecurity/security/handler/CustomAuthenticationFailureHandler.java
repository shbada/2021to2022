package io.security.corespringsecurity.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
설정 : SecurityConfig.java (.failureHandler(customAuthenticationFailureHandler))
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /**
     * 인증 검증할때 인증 실패하는 경우 인증 예외를 발생시킨다.
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 예외를 처리한다.
        // 예외메시지를 클라이언트에게 보여주자.
        String errorMessage = "Invalid Username or Password";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username or Password";
        } else if (exception instanceof InsufficientAuthenticationException) { // CustomAuthenticationProvider.java
            errorMessage = "Invalid Secret key";
        }

        // /login?error=true&exception 를 전체 경로로 인식한다. 따라서 접근이 가능하도록 해줘야한다.
        // SecurityConfig.java (.antMatchers("/", "/users", "user/login/**", "/login*").permitAll())
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
