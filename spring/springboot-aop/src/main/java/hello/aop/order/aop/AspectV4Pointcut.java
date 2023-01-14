package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect // aspectj 가 제공하는 어노테이션
public class AspectV4Pointcut {
    /**
     * Pointcuts 외부 포인트컷 파일의 것을 사용하기
     * 패키지명.클래스명.메서드
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("hello.aop.order.aop.Pointcuts.allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    // 포인트컷 &&, ||, ! 3가지 조합이 가능하다.
    @Around("hello.aop.order.aop.Pointcuts.orderAndService())")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

            Object result = joinPoint.proceed();

            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());

            return result;
        } catch (Exception e) {
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature()); }
    }
}
