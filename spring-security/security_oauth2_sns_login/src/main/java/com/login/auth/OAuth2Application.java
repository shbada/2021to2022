package com.login.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;

@SpringBootApplication
public class OAuth2Application {
    // CommonOAuth2Provider commonOAuth2Provider;

    public static void main(String[] args) {
        SpringApplication.run(OAuth2Application.class, args);
    }

}
