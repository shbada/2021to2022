package io.security.corespringsecurity.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
설정 : SecurityConfig.java (.successHandler(customAuthenticationSuccessHandler))
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    // 이전에 사용자가 요청하고자했던 화면 값을 가지고있다.
    private RequestCache requestCache = new HttpSessionRequestCache();

    // 최종적으로 이동
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * authentication : 인증 성공 후, 인증 객체
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 사용자가 인증이 성공한 이후 그 다음 이동할 페이지를 우리가 설정한다.
        // 사용자가 인증에 실패했다가 다시 인증에 성공했을때 시도했던 그 페이지로 다시 이동시켜줘야한다.

        setDefaultTargetUrl("/"); // 기본 url

        // 사용자가 인증하기전에 요청했던 정보들을 담고있는 객체
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) { // '마이페이지' 접근시, 인증을 받지 않았으므로 '로그인' 페이지로 이동된다. 이때 인증시!
            String targetUrl = savedRequest.getRedirectUrl(); // 이전에 가고자했던 url
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else { // '로그인' 버튼을 눌러서 왔을때는 요청했을때의 페이지가 없겠다.
            // 기본적으로 이동할 페이지 설정
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl()); // setDefaultTargetUrl() 한 값
        }
    }
}
