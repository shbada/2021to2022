package com.login.auth.config;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

/**
 * DefaultOAuth2UserService
 * facebook, 카카오 등이 따르는 스펙
 */
@Service
public class SpOAuth2UserService extends DefaultOAuth2UserService {

    /**
     * 유저 정보가 오면 load
     * @param userRequest
     * @return
     * @throws OAuth2AuthenticationException
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // user 가 들어왔을때
        // 이 사용자를 우리 SpUser 사용자로 변환 또는 등록하는 과정을 거칠 수 있다.
        return super.loadUser(userRequest);
    }

}
