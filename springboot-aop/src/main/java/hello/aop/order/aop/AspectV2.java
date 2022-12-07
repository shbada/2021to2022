package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect // aspectj 가 제공하는 어노테이션
// AspectV1 을 빈으로 꼭 등록해줘야한다!
public class AspectV2 {
    /**
     * 여러곳에서 사용이 가능하다.
     * Pointcut 반환타입 : void
     * 내부에서 사용이면 private, 다른 Aspect 파일에서 사용하려면 public
     *
     */
    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    private void allOrder() {} //pointcut signature

    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
