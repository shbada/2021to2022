package com.example.stock_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 동시성 문제 해결을 위한 프로젝트
 * - 동일한 하나의 데이터에 2개 이상의 쓰레드, 혹은 세션에서 가변 데이터를 동시에 제어할때 발생하는 문제
 */
@SpringBootApplication
public class StockProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockProjectApplication.class, args);
    }

}
