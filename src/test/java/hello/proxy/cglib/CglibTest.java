package hello.proxy.cglib;

import hello.proxy.cglib.code.TImeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    /**
     * CGLIB 는 상속을 사용한다.
     * 부모 클래스의 생성자를 체크해야한다. 자식 클래스를 동적으로 생성하므로 기본 생성자가 필요하다.
     * final 키워드가 붙으면 상속이 불가능하므로 CGLIB 에서는 예외가 발생한다.
     * final 키워드가 붙으면 메서드 오버라이딩이 불가능하다. CGLIB에서는 프록시 로직이 동작하지 않는다.
     */
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        // CGLIB는 Enhancer 를 사용해서 프록시를 생성한다.
        Enhancer enhancer = new Enhancer();

        // CGLIB 는 구체 클래스를 상속받아서 프록시를 생성할 수 있다.
        // 어떤 구체 클래스를 상속 받을지 지정한다.
        enhancer.setSuperclass(ConcreteService.class);

        // 프록시에 적용할 실행 로직을 할당
        enhancer.setCallback(new TImeMethodInterceptor(target));

        // enhancer.setSuperclass(ConcreteService.class); 에서 지정한 클래스를 상속받아서 프록시를 만든다.
        ConcreteService proxy = (ConcreteService) enhancer.create();

        // hello.proxy.common.service.ConcreteService
        log.info("targetClass={}", target.getClass());

        // hello.proxy.common.service.ConcreteService$$EnhancerByCGLIB$$25d6b0e3
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }
}
