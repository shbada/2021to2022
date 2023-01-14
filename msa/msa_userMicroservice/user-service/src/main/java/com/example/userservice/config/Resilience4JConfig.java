package com.example.userservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
            .failureRateThreshold(4) /* CiruitBreak 를 열지 결정 (100번에 4번정도 실패했을 경우 연다) */
            .waitDurationInOpenState(Duration.ofMillis(1000)) /*open 상태로 유지하는 지속 기간 */
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) /* circuitBreaker가 닫힐떄 통화 결과를 기록하는데, 사용되는 슬라이딩 창의 유형 구성 */
            .slidingWindowSize(2) /* circuitBreakr가 닫힐때 호출결과를 기록하는데, 사용되는 슬라이딩 창의 크기 */
            .build();

    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
            .timeoutDuration(Duration.ofSeconds(4)) /* 4초동안 응답이 없을 경우 실패로 간주 */
            .build();

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(timeLimiterConfig)
                    .circuitBreakerConfig(circuitBreakerConfig)
                    .build()
        );
    }
}
