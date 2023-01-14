package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * application.yml 에 설정했던 cloud:gateway:routes 설정 대체 가능 (java 코드)
 */
//@Configuration
public class FilterConfig {
    //@Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/first-service/**") // 요청이 오면
                        .filters(f -> f.addRequestHeader("first-request", "first-request-header") // key,value 형태
                                .addResponseHeader("first-response", "first-response-header")
                        ) // request / response 필더 2개 등록 가능
                        .uri("http://localhost:8081") // 여기로 이동
                )
                .route(r -> r.path("/second-service/**") // 요청이 오면
                        .filters(f -> f.addRequestHeader("second-request", "second-request-header") // key,value 형태
                                .addResponseHeader("second-response", "second-response-header")
                        ) // request / response 필더 2개 등록 가능
                        .uri("http://localhost:8082") // 여기로 이동
                )
                .build();
    }
}
