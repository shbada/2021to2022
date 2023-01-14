package com.example.csvfiledatabatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class CsvFileDataBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvFileDataBatchApplication.class, args);
    }

}
