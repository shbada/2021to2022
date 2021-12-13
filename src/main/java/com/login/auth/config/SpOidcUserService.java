package com.login.auth.config;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

/**
 * 구글이 따르는 OidcUserService
 */
@Service
public class SpOidcUserService extends OidcUserService {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        // user 가 들어왔을때
        // 이 사용자를 우리 SpUser 사용자로 변환 또는 등록하는 과정을 거칠 수 있다.

        return super.loadUser(userRequest);
    }

}
