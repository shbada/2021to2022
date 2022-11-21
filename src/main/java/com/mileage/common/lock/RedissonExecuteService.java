package com.mileage.common.lock;

import com.mileage.common.exception.BadRequestException;
import com.mileage.common.exception.LockFailException;
import com.mileage.domain.Mileage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedissonExecuteService {
    private final RedissonClient redissonClient;

    public Mileage execute(Long mileageIdx, Callable<Mileage> callable) {
        /* create key */
        String key = String.valueOf(mileageIdx);

        /* get rLock 객체 */
        RLock rLock = redissonClient.getLock(key);

        try {
            /* get lock */
            boolean isPossible = rLock.tryLock(3L, 3L, TimeUnit.SECONDS);

            if (!isPossible) {
                throw new LockFailException();
            }

            log.info("Redisson Lock Key : {}", key);

            /* service call */
            return callable.call();
        } catch (BadRequestException be) {
            throw be;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LockFailException();
        } finally {
            rLock.unlock();
        }
    }
}
