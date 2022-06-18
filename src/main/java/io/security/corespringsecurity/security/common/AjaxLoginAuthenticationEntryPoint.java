package io.security.corespringsecurity.security.common;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1) 인증을 받지 못한 유저가 자원에 접근하려는 경우 - 인증이 필요한 경우 -> 인증을 다시 받도록 처리해야한다.
 * 2) 인증을 받은 유저가 접근하려는 자원의 권한을 충족하지 못하는 경우 -> 권한이 없으므로 처리해야한다.
 *
 * FilterSecurityInterceptor.java
 * 권한 체크하는 클래스
 * 그 유저가 해당 자원에 접근할 자격이 있는지를 판단한다.
 * 2가지 일을 한다.
 * 1) 인증을 받지 않은 사용자가 자원에 접근했을 경우
 * - AccessDeniedException 인가 예외 발생
 * - ExceptionTranslationFilter.java 가 위 예외를 받는다.
 *    - 익명 사용자인 경우 -> sendStartAuthentication() 실행
 *       - authenticationEntryPoint.commence(request, response, reason); 수행
 *    - 익명 사용자가 아닌 인증 받은 사용자가 자원에 접근했는데 그때 AccessDeniedException 발생했을때
 *       - accessDeniedHandler.handle(request, response, exception); 수행
 */

public class AjaxLoginAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 익명사용자의 경우
     * AjaxLoginAuthenticationEntryPoint 의 commence()를 호출해서 최종적으로 클라이언트에 전달한다.
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
    }
}
