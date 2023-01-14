package com.mileage.common.lock;

import com.mileage.common.exception.BadRequestException;
import com.mileage.common.exception.LockFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AspectRedissonLock {
    private final RedissonClient redissonClient;
    private final RedissonCallTransaction redissonCallTransaction;

    @Around("@annotation(com.mileage.common.lock.RedissonLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RedissonLock distributeLock = method.getAnnotation(RedissonLock.class);

        /* create key */
        String key = this.createKey(signature.getParameterNames(), joinPoint.getArgs(), distributeLock.key().split(","));

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
            return redissonCallTransaction.proceed(joinPoint);
        } catch (BadRequestException be) {
            throw be;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LockFailException();
        } finally {
            rLock.unlock();
        }
    }

    /**
     * Redisson Key Create
     * @param parameterNames
     * @param args
     * @param keys
     * @return
     */
    private String createKey(String[] parameterNames, Object[] args, String[] keys) {
        StringBuilder resultKey = new StringBuilder();

        /* key = parameterName */
        for (int i = 0; i < parameterNames.length; i++) {
            for (String key : keys) {
                if (parameterNames[i].equals(key)) {
                    resultKey.append(args[i]);
                }
            }
        }

        return resultKey.toString();
    }
}
