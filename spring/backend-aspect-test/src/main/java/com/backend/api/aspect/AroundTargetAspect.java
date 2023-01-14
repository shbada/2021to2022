package com.backend.api.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AroundTargetAspect {

    @Around("@annotation(com.backend.api.aspect.AroundTarget)")
    public void aroundTarget(ProceedingJoinPoint pjp) {
        log.info("annotation aspect test...");
    }
}
