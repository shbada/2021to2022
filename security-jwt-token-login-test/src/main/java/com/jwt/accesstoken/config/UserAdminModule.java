package com.jwt.accesstoken.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.jwt.accesstoken")
@EntityScan(basePackages = {
        "com.jwt.accesstoken.user.domain"
})
@EnableJpaRepositories(basePackages = {
        "com.jwt.accesstoken.user.repository"
})
public class UserAdminModule {
}
