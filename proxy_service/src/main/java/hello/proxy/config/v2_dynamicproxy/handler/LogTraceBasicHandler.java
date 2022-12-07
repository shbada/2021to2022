package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler 를 구현해서 JDK 동적 프록시에서 사용된다.
 */
public class LogTraceBasicHandler implements InvocationHandler {
    private final Object target; // 프록시가 호출할 대상
    private final LogTrace logTrace;

    public  LogTraceBasicHandler(Object target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    /**
     * @param proxy 프록시 자신
     * @param method 호출한 메서드
     * @param args 메서드를 호출할 때 전달한 인수
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus status = null;

        try {
            // LogTrace 에서 사용할 메시지
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);

            // 로직 호출
            Object result = method.invoke(target, message);

            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.end(status);
            throw e;
        }
    }
}
