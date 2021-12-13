package com.login.auth.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.login.auth")
@EntityScan(basePackages = {
        "com.login.auth.user.domain"
})
@EnableJpaRepositories(basePackages = {
        "com.login.auth.user.repository"
})
public class UserAdminModule {
}
