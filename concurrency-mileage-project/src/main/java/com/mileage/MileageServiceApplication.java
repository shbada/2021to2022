package com.mileage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MileageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MileageServiceApplication.class, args);
    }

}
