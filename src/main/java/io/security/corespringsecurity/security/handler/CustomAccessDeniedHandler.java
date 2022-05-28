package io.security.corespringsecurity.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증 예외 : 해당 인증을 처리하고있는 필터가 처리
 * 인가 예외 : ExceptionTranslationFilter 가 처리
 */
/*
SecurityConfig.java 설정 추가
.exceptionHandling()
.accessDeniedHandler(accessDeniedHandler())

MEMBER 권한 계정으로 로그인 후, ADMIN 권한의 메뉴 클릭시 발생!
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private String errorPage;

    /**
     * 인가 예외 AccessDeniedException 가 전달됨
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String deniedUrl = errorPage + "?exception=" + accessDeniedException.getMessage();
        response.sendRedirect(deniedUrl);
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}
