package com.example.apigatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest; // RxJava
import org.springframework.http.server.reactive.ServerHttpResponse; // RxJava
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Global Filter > Custom Filter > Logging Filter 순차적 실행
 */
@Component
@Slf4j
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre Filter
        return (exchange, chain) -> { /* lamda */
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Custom Pre filter : request id > {}", request.getId());

            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> { /* exchange 와 연결, 비동식 Mono 사용 */
                log.info("Custom Post filter : response id > {}", response.getStatusCode());
            }));


        };
    }

    public static class Config {
        // Put the configuration properties

    }
}
