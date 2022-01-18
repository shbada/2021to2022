package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Hello target = new Hello();

        /**
         * callA(), callB 를 동적으로 호출하고싶다.
         * 호출 메서드만 다르고 전체 코드 흐름이 완전히 동일하기 때문이다.
         */
        // 공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름
        log.info("result={}", result1);
        // 공통 로직1 종료

        // 공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); // 호출하는 메서드가 다름
        log.info("result={}", result2);
        // 공통 로직2 종료
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B"; }
    }

    @Test
    void reflection1() throws Exception {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    /**
     * 리플렉션을 사용하면 클래스와 메서드의 메타정보를 사용해서 애플리케이션을 동적으로 유연하게 만들 수 있다.
     * 하지만 리플렉션 기술은 런타임에 동작하기 때문에, 컴파일 시점에 오류를 잡을 수 없다.
     * 가장 좋은 오류는 개발자가 즉시 확인할 수 있는 컴파일 오류이고,
     * 가장 무서운 오류는 사용자가 직접 실행할 때 발생하는 런타임 오류다.
     *
     * 따라서 리플렉션은 일반적으로 사용하면 안된다.
     * @throws Exception
     */
    @Test
    void reflection2() throws Exception {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        // callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");

        // target : 실제 실행할 인스턴스 정보
        // target 의 method 여야 호출이 가능하다.
        // method.invoke(target) 를 사용할 때 호출할 클래스와 메서드 정보가 서로 다르면 예외가 발생한다.
        Object result = method.invoke(target);
        log.info("result={}", result);
    }
}
