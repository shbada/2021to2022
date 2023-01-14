package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * InvocationHandler 를 구현해서 JDK 동적 프록시에서 사용된다.
 */
public class LogTraceFilterHandler implements InvocationHandler {
    private final Object target; // 프록시가 호출할 대상
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
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
        // 메서드 이름 필더
        String methodName = method.getName();

        // save, request, reques*, *est (패턴 적용)
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }

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
