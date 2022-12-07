package com.test.technology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
/** feign add */
public class TechnologyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnologyApplication.class, args);
    }

}
