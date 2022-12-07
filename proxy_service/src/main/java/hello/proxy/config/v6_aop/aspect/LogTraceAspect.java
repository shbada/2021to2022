package hello.proxy.config.v6_aop.aspect;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/**
 * 자동 프록기 생성기(AnnotationAwareAspectJAutoProxyCreator)는 Advisor를 자동으로 찾아와서
 * 필요한 곳에 프록시를 생성하고 적용해준다.
 *
 * 자동 프록기 생성기는 @Aspect 를 찾아서 이것을 Advisor 로 만들어준다.
 *
 * 1. 실행 : 스프링 애플리케이션 로딩 시점에 자동 프록시 생성기를 호출한다.
 * 2. 모든 @Aspect 빈 조회 :  자동 프록시 생성기는 스프링 컨테이너에서 @Aspect 어노테이션이 붙은 스프링 빈을 모두 조회한다.
 * 3. 어드바이저 생성 : @Aspect 어드바이저 빌더를 통해 @Aspect 어노테이션 정보를 기반으로 어드바이저를 생성한다.
 * 4. @Aspect 기반 어드바이저 저장 : 생성한 어드바이저를 @Aspect 어드바이저 빌더 내부에 저장한다.
 */
@Slf4j
@Aspect // 어노테이션 기반 프록시를 적용할때 필수
public class LogTraceAspect {
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    // @Around 메서드 : 어드바이스(Advice)가 된다.
    @Around("execution(* hello.proxy.app..*(..))") // 포인트컷 표현식
    // execute 메서드 : 어드바이스(Advice)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // ProceedingJoinPoint joinPoint : MethodInvocation invocation 과 유사한 기능이다.

        TraceStatus status = null;

        try {
            // message 가져오기
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 로직 호출 (실제 대상 target 호출)
            Object result = joinPoint.proceed();

            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.end(status);
            throw e;
        }
    }
}
