package hello.proxy.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TimeAdvice implements MethodInterceptor {
    /**
     * target 을 안넣어줘도 된다.
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");

        long startTime = System.currentTimeMillis();

        // proxy -> proxy
        // target : AImpl 또는 BImpl,
//        Object result = method.invoke(target, args);

        // target 을 찾아서 실제 객체의 메서드를 호출해준다.
        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();

        long resultTime = endTime -  startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
}
