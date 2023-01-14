package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {
    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient concreteClient = new ConcreteClient(concreteLogic);

        concreteClient.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic); // 실제 대상 concreteLogic

        // 실제 대상을 갖고있는 timeProxy
        // ConcreteClient 안의 concreteLogic.operation() 는 프록시 객체를 호출 (timeProxy)
        // TImeProxy 안에서의 concreteLogic.operation(); 얘가 실제 대상의 메서드를 호출
        ConcreteClient concreteClient = new ConcreteClient(timeProxy); // 프록시

        /*
        다형성에 의해 ConcreteLogic, timeProxy 둘다 ConcreteClient(여기)로 들어갈 수 있다.

        인터페이스가 없어도 클래스 기반의 프록시 적용도 가능하다.
        다형성은 인터페이스, 클래스를 구분하지 않고 모두 적용된다. 해당 타입과 그 타입의 하위 타입은 모두 다형성의 대상이 된다.
         */

        concreteClient.execute();
    }
}
