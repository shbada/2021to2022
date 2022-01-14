package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import hello.proxy.pureproxy.proxy.code.Subject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    /**
     * 프록시를 사용하지 않는 예제
     */
    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        /* 같은 메서드 3번 호출 */
        client.execute();
        client.execute();
        client.execute();
    }

    /**
     * 프록시 호출 예제
     */
    @Test
    void cacheProxyTest() {
        // 실제 객체
        Subject realSubject = new RealSubject();

        // proxy
        Subject cacheProxy = new CacheProxy(realSubject);

        // client
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        // 첫번째만 실제 객체 호출
        client.execute();

        // 그 이후는 프록시의 캐시 처리
        client.execute();
        client.execute();
    }
}
