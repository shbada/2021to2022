package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect // aspectj 가 제공하는 어노테이션
// AspectV1 을 빈으로 꼭 등록해줘야한다!
public class AspectV3 {
    /**
     * 여러곳에서 사용이 가능하다.
     * Pointcut 반환타입 : void
     * 내부에서 사용이면 private, 다른 Aspect 파일에서 사용하려면 public
     *
     */
    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))") //pointcut expression
    private void allOrder() {} //pointcut signature

    //클래스 이름 패턴이 *Service (보통 트랜잭션은 service 에 들어가니깐 service 로 해보자)
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}


    @Around("allOrder()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    // hello.aop.order 패키지와 하위 패키지 이면서 클래스 이름 패턴이 *Service
    // 포인트컷 &&, ||, ! 3가지 조합이 가능하다.
    @Around("allOrder() && allService()")
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
