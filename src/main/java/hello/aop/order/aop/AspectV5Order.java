package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
// @Aspect // aspectj 가 제공하는 어노테이션
public class AspectV5Order {
    /**
     * ORDER 은 클래스 명 위에 선언 가능하다.
     * 각 Pointcut 을 내부 static 클래스로 선언하여 Order 로 순서를 정하자.
     */
    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {
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

}
