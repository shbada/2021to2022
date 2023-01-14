package com.example.stock_project.facade;

import com.example.stock_project.service.StockNonSynchronizedService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonLockStockFacade {

    private final RedissonClient redissonClient;

    private final StockNonSynchronizedService stockService;

    public RedissonLockStockFacade(final RedissonClient redissonClient, final StockNonSynchronizedService stockService) {
        this.redissonClient = redissonClient;
        this.stockService = stockService;
    }

    /**
     * [Redisson]
     * Pub-sub 기반으로 Lock 구현 제공
     * Pub-Sub 방식이란, 채널을 하나 만들고
     * 락을 점유중인 스레드가 락을 해제했음을 대기중인 스레드에게 알려주면 대기중인 스레드가 락 점유를 시도하는 방식
     * 이 방식은, Lettuce와 다르게 대부분 별도의 Retry 방식을 작성하지 않아도된다.
     *
     * Thread-1 ---(나 끝났어)---> Channel ---(락 획득 시도해)---> thread2
     *
     * (실행)
     * 1) [1]번의 cli - subscribe 명령어를 사용하여 ch1 을 구독
     * : subscribe ch1
     *
     * 2) [2]번의 cli - publish 명령어를 사용하여 메세지를 전달
     * : publish ch1 hello
     *
     * 3) [1]번의 cli - 1번 은 ch1 을 구독하고 있기 때문에, 메세지 출력
     *
     * (장점)
     * Lettuce 와 다르게 Redisson 은 계속 락 획득을 계속 시도하는게 아니기 때문에 Redis의 부하를 줄일 수 있다.
     */
    public void decrease(final Long productId, final Long quantity) throws InterruptedException {
        // key 로 Lock 객체 가져온다.
        final RLock lock = redissonClient.getLock(productId.toString());

        try {
            //획득 시도 시간, 락 점유 시간
//            boolean isAvailable = lock.tryLock(10, 1, TimeUnit.SECONDS);

            if (!lock.tryLock(30, 1, TimeUnit.SECONDS)) {
                System.out.println("redisson getLock timeout");
                return;
            }

            stockService.decrease(productId, quantity);

        } finally {
            // unlock the lock object
            lock.unlock();
        }
    }
}
