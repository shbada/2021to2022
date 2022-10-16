package com.example.stock_project.facade;

import com.example.stock_project.repository.RedisRepository;
import com.example.stock_project.service.StockNonSynchronizedService;
import org.springframework.stereotype.Component;

@Component
public class LettuceLockStockFacade {

    private final RedisRepository redisRepository;

    private final StockNonSynchronizedService stockService;

    public LettuceLockStockFacade(final RedisRepository redisRepository, final StockNonSynchronizedService stockService) {
        this.redisRepository = redisRepository;
        this.stockService = stockService;
    }

    /**
     * [Lettuce]
     * Setnx 명령어를 활용하여 분산락을 구현
     * (Set if not Exist - key:value를 Set 할때, 기존의 값이 없을 때만 Set 하는 명령어)
     * Setnx 는 Spin Lock 방식이므로 retry 로직을 개발자가 작성해줘야한다.
     * Spin Lock 이란, Lock 을 획득하려는 스레드가 Lock을 획득할 수 있는지 확인하면서 반복적으로 시도하는 방법입니다.
     *
     * (단점)
     * Sprin Lock 방식이, Lock 을 얻을 떄까지 Lock 얻기를 시도하기 떄문에, 계속해서 Redis 에 접근해서 Redis에 부하를 줄 수 있다는 단점이 존재
     *
     * (명령어)
     * setnx (key) (value)
     * 1 이라는 key 로 맨처음 삽입할때는 성공하지만, 그 이후로는 실패
     * del (key) 명렁어로 해당 key의 데이터를 삭제하고 다시 삽입하면 성공
     *
     * RedisRepository.java 생성
     */
    public void decrease(final Long productId, final Long quantity) throws InterruptedException {
        while (!redisRepository.lock(productId)) { // Lock 획득 시도
            Thread.sleep(100); // 부하를 줄여줘본다.
        }

        try {
            // lock 획득 성공시
            stockService.decrease(productId, quantity);
        } finally {
            // 락 해제
            redisRepository.unlock(productId);
        }
    }
}
