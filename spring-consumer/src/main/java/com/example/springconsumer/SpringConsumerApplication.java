package com.example.springconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringConsumerApplication {
    private static String TOPIC_NAME = "test";

    public static void main(String[] args) {
        SpringApplication.run(SpringConsumerApplication.class, args);
    }

}
