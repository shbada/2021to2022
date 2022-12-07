package io.security.corespringsecurity.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class FormWebAuthenticationDetails extends WebAuthenticationDetails {
    // 사용자가 설정하는 추가적인 파라미터를 설정한다.
    // 1개만 추가해보자.
    // 화면도 수정 login.html : <input type="hidden" th:value="secret" name="secret_key" />
    private String secretKey;

    /**
     * 생성자
     * @param request
     */
    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        secretKey = request.getParameter("secret_key");
    }

    /**
     * getter
     * @return
     */
    public String getSecretKey() {
        return secretKey;
    }
}
