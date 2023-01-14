package com.example.netflix_zull_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class NetflixZullServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixZullServiceApplication.class, args);
    }

}
