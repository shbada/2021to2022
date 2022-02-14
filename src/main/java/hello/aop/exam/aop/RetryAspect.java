package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {
    @Around("@annotation(retry)") // 어노테이션을 매개변수로 받음 (Retry retry)
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        log.info("[retry] {} retry={}", joinPoint.getSignature(), retry);

        // retry.value() 를 통해서 애노테이션에 지정한 값을 가져올 수 있다.
        int maxRetry = retry.value();
        Exception exceptionHolder = null;

        /* 예외가 발생해서 결과가 정상 반환되지 않으면 retry.value() 만큼 재시도한다 */
        for (int retryCount = 1; retryCount <= maxRetry; retryCount++) {
            try {
                log.info("[retry] try count={}/{}", retryCount, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }
}
