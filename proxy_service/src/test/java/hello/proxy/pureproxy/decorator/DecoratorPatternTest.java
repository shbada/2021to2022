package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 데코레이터 패턴은 여러 기능들을 계속해서 꾸밀 수 있다.
 * 클라이언트는 코드 변경이 필요없다. (DecoratorPatternClient)
 */
@Slf4j
public class DecoratorPatternTest {
    /**
     * 데코레이터 패턴 적용하지 않은 경우
     */
    @Test
    void noDecorator() {
        Component realComponent = new RealComponent();
        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(realComponent);

        decoratorPatternClient.execute();
    }

    /**
     * 데코레이터 패턴 적용
     */
    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);

        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(messageDecorator);
        decoratorPatternClient.execute();
    }

    /**
     * 데코레이터 패턴 적용
     */
    @Test
    void decorator2() {
        Component realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);

        DecoratorPatternClient decoratorPatternClient = new DecoratorPatternClient(timeDecorator);
        decoratorPatternClient.execute();
    }
}
