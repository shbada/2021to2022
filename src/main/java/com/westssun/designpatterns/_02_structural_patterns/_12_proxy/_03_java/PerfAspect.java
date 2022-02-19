package com.westssun.designpatterns._02_structural_patterns._12_proxy._03_java;

import org.springframework.stereotype.Component;

/**
 * Spring AOP
 */
@Aspect
@Component // 빈 등록
public class PerfAspect {

    @Around("bean(gameService)")
    public void timestamp(ProceedingJoinPoint point) throws Throwable {
        long before = System.currentTimeMillis();
        point.proceed();
        System.out.println(System.currentTimeMillis() - before);
    }
}
