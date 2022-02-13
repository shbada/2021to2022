package com.westssun.designpatterns._01_creational_patterns._01_singleton._마무리_실제예제._01_singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public String hello() {
        return "hello";
    }

}
