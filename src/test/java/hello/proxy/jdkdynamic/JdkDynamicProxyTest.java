package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * 프록시를 계속 만들어야하는 상황이 사라진다.
 * 동적 프록시를 사용했기 때문.
 */
@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        // proxy
        // 반환타입 Object
        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader() // 어느 클래스로더에 할지
                , new Class[]{AInterface.class}, handler); // handler : 프록시가 사용해야할 로직

        proxy.call();

        log.info("targetClass={}", target.getClass()); // hello.proxy.jdkdynamic.code.AImpl
        log.info("proxyClass={}", proxy.getClass()); // com.sun.proxy.$Proxy12
    }

    @Test
    void dynamicB() {
        BInterface target = new BImpl();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(target);

        // proxy 객체를 여기서 만들어주니깐 개발자는 TimeInvocationHandler 만 개발하면 된다.
        // proxy
        // 반환타입 Object
        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader() // 어느 클래스로더에 할지
                , new Class[]{BInterface.class}, timeInvocationHandler); // timeInvocationHandler : 프록시가 사용해야할 로직

        proxy.call();

        log.info("targetClass={}", target.getClass()); // hello.proxy.jdkdynamic.code.AImpl
        log.info("proxyClass={}", proxy.getClass()); // com.sun.proxy.$Proxy12
    }
}
