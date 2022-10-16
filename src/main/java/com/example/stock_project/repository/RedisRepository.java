package com.example.stock_project.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Lettuce vs Redisson
 * [Lettuce]
 * - 구현이 간단하다
 * - Spring data redis를 이용하면 lettuce가 기본이기 떄문에 별도의 라이브러리를 사용하지 않아도 된다.
 * - Spin Lock 방식이기 때문에 동시에 많은 스레드가 lock 획득 대기 상태라면 redis에 부하가 갈 수 있다.
 *
 *
 * [Redisson]
 * - 락 획득 재시도를 기본으로 제공한다.
 * - pub-sub 방식으로 구현이 되어있기 때문에 lettuce 와 비교했을 때 redis에 부하가 덜 간다.
 * - 별도의 라이브러리를 사용해야한다.
 * - lock을 라이브러리 차원에서 제공해주기 때문에 사용법을 공부해야 한다.
 */
@Component
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisRepository(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock(final Long key) {
        String generatedKey = generateKey(key);
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generatedKey, "lock", Duration.ofMillis(3_000));
    }

    public Boolean unlock(final Long key) {
        String generatedKey = generateKey(key);
        return redisTemplate.delete(generatedKey);
    }

    public String generateKey(final Long key) {
        return key.toString();
    }
}
