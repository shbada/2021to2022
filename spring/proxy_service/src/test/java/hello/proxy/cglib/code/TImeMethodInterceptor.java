package hello.proxy.cglib.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * MethodInterceptor : 스프링의 CGLIB 가 제공해주는 인터페이스
 */
@Slf4j
public class TImeMethodInterceptor implements MethodInterceptor {
    // 항상 프록시는 호출할 대상이 필요하다.
    private final Object target;

    public TImeMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");

        long startTime = System.currentTimeMillis();

        // proxy -> proxy
        // target : AImpl 또는 BImpl,
        // Object result = method.invoke(target, args); // 이렇게 해도 되긴됨

        Object result = methodProxy.invoke(target, args);// 이게 더 빠르다. (매뉴얼에 그렇게 되어있음)

        long endTime = System.currentTimeMillis();

        long resultTime = endTime -  startTime;
        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
}
