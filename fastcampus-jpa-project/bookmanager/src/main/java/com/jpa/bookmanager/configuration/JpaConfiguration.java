package com.jpa.bookmanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Test 용  (// JPA metamodel must not be empty! 에러 발생)
@Configuration
@EnableJpaAuditing
public class JpaConfiguration {
}
