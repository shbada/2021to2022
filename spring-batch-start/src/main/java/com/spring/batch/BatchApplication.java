package com.spring.batch;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.JpaBatchConfigurer;

@SpringBootApplication
@EnableBatchProcessing
public class BatchApplication {
    // SimpleBatchConfiguration
    // BatchConfigurer
    // BasicBatchConfigurer
    // JpaBatchConfigurer
    // BatchAutoConfiguration

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

}
