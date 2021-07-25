package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Global Filter > Custom Filter > Logging Filter 순차적 실행
 */
@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {
    public LoggingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // Custom Pre Filter
//        return (exchange, chain) -> { /* lamda */
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            /* config. xx -> .yml 파일 안에 선언되어있다 */
//            log.info("Global Pre baseMessage : request id > {}", config.getBaseMessage());
//
//            if (config.isPreLogger()) {
//                log.info("Global Pre Start : request id > {}", request.getId());
//            }
//
//            // CustomFilter.java 의 로그도 함께 찍히게된다.
//
//            // Custom Post Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> { /* exchange 와 연결, 비동식 Mono 사용 */
//                if (config.isPostLogger()) {
//                    log.info("Global Post End : response id > {}", response.getStatusCode());
//                }
//            }));
//
//
//        };

        /** OrderedGatewayFilter 을 우선순위를 가장 높게(Ordered.HIGHEST_PRECEDENCE) 지정해서 Logging 이 제일 먼저 실행되었다 */
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // GlobalFilter.java 의 로그도 함께 찍히게된다.

            /* config. xx -> .yml 파일 안에 선언되어있다 */
            log.info("Logging Pre baseMessage : request id > {}", config.getBaseMessage());

            if (config.isPreLogger()) {
                log.info("Logging Pre Filter : request id > {}", request.getId());
            }

            // CustomFilter.java 의 로그도 함께 찍히게된다.

            // Custom Post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(() -> { /* exchange 와 연결, 비동식 Mono 사용 */
                if (config.isPostLogger()) {
                    log.info("Logging Post Filter : response id > {}", response.getStatusCode());
                }
            }));
        }, Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }

    @Data
    public static class Config {
        // Put the configuration properties
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
