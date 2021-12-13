package com.login.auth.config;

import com.login.auth.user.domain.SpOauth2User;
import com.login.auth.user.domain.SpUser;
import com.login.auth.user.service.SpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SnsLoginSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SpUserService userService;
    private final SpOAuth2UserService oAuth2UserService;
    private final SpOidcUserService oidcUserService;

    /* 구글이 OidcUserService 을 제공해준다. */
    // private OidcUserService oidcUserService; // loadUser (OidAuthorizationCodeAuthenticationProvider)

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* oauth2 를 사용하여 사용자를 받겠다는 의미 */
        http.oauth2Login(
                oauth2 -> oauth2.userInfoEndpoint(
                        userInfo -> userInfo.userService(oAuth2UserService)
                                .oidcUserService(oidcUserService)
                ).successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        // 인증이 되었다면 authentication 객체가 있을것
                        Object principal = authentication.getPrincipal();

                        if (principal instanceof OAuth2User) {
                            /* OidcUser */
                            if (principal instanceof OidcUser) {
                                // google
                                SpOauth2User googleUser = SpOauth2User.OAuth2Provider.google.convert((OAuth2User) principal);
                                SpUser user = userService.loadUser(googleUser);

                                /* SecurityContextHolder set */
                                SecurityContextHolder.getContext().setAuthentication(
                                        new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities())
                                );
                            } else {
                                // naver, or kakao, facebook
                                SpOauth2User naverUser = SpOauth2User.OAuth2Provider.naver.convert((OAuth2User) principal);
                                SpUser user = userService.loadUser(naverUser);

                                /* SecurityContextHolder set */
                                SecurityContextHolder.getContext().setAuthentication(
                                        new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities())
                                );
                            }
                        }

                    }
                })
        );
    }
}
