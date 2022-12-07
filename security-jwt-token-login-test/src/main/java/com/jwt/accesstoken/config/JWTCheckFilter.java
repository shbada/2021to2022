package com.jwt.accesstoken.config;

import com.jwt.accesstoken.user.domain.SpUser;
import com.jwt.accesstoken.user.service.SpUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 매번 request 가 올때마다 token을 검사해서
 * securityContextHolder 에 user principal 정보를 채워주는 역할
 */
public class JWTCheckFilter extends BasicAuthenticationFilter {
    private SpUserService userService;

    public JWTCheckFilter(AuthenticationManager authenticationManager, SpUserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // token 검증
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearer == null || !bearer.startsWith("Bearer ")) {
            // 요청에 들어가야 에러처리도 되기 때문에 패스하여 넘겨줌
            chain.doFilter(request, response);
            return;
        }

        // Bearer 부분 제외
        String token = bearer.substring("Bearer ".length());

        /* token 유효 체크 */
        VerifyResult result = JWTUtil.verify(token);

        if (result.isSuccess()) { /* 유효 */
            // username 을 가지고 userService 에서 해당 유저 정보의 타당성 체크
            SpUser user = (SpUser) userService.loadUserByUsername(result.getUsername());

            // userToken 생성
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), null, user.getAuthorities()
            );

            // SecurityContextHolder setting
            SecurityContextHolder.getContext().setAuthentication(userToken);

            // call
            chain.doFilter(request, response);
        } else { /* not valid */
            throw new AuthenticationException("Token is not valid");
        }

        super.doFilterInternal(request, response, chain);
    }
}
