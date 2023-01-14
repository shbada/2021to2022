package hello.aop.pointcut;

import hello.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Slf4j
@Import({AtTargetAtWithinTest.Config.class})
@SpringBootTest
public class AtTargetAtWithinTest {
    @Autowired
    Child child;

    @Test
    void success() {
        log.info("child Proxy={}", child.getClass());
        // [@target] void hello.aop.pointcut.AtTargetAtWithinTest$Child.childMethod()
        child.childMethod(); //부모, 자식 모두 있는 메서드

        // [@within] void hello.aop.pointcut.AtTargetAtWithinTest$Child.childMethod()
        // [@target] void hello.aop.pointcut.AtTargetAtWithinTest$Parent.parentMethod()
        child.parentMethod(); //부모 클래스만 있는 메서드
    }

    static class Config {
        @Bean
        public Parent parent() {
            return new Parent();
        }

        @Bean
        public Child child() {
            return new Child();
        }

        @Bean
        public AtTargetAtWithinAspect atTargetAtWithinAspect() {
            return new AtTargetAtWithinAspect();
        }
    }

    static class Parent {
        public void parentMethod() {
        } // 부모에만 있는 메서드
    }

    @ClassAop
    static class Child extends Parent {
        public void childMethod() {
        }
    }

    /**
     * 다음 포인트컷 지시자는 단독으로 사용하면 안된다. args, @args, @target
     * 예제를 보면 execution(* hello.aop..*(..)) 를 통해 적용 대상을 줄여줬다.
     * args, @args, @target 은 실제 객체 인스턴스가 생성되고 실행될 때 어드바이스 적용 여부를 확인할 수 있다.
     *
     * 런타임이 될때 판단이 되는데, 런타임이 되서 실행 시점에 인스턴스가 넘어와야 어드바이스를 적용할지를 판단하는데,
     * 프록시가 있어야 판단도 가능하다.
     * 스프링에서 적용대상을 줄이지 않으면 모든 대상에 프록시를 걸려고 한다.
     *
     * 모든 스프링 빈에 AOP 프록시를 적용하려고 하면
     * 스프링이 내부에서 사용하는 빈 중에는 final 로 지정된 빈들도 있기 때문에 오류가 발생할 수 있다.
     */
    @Slf4j
    @Aspect
    static class AtTargetAtWithinAspect {
        // @target: 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 적용
        // @ClassAop 가 있는것
        @Around("execution(* hello.aop..*(..)) && @target(hello.aop.member.annotation.ClassAop)")
        public Object atTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            // childMethod, parentMethod
            log.info("[@target] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // @within: 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용되지 않음
        @Around("execution(* hello.aop..*(..)) && @within(hello.aop.member.annotation.ClassAop)")
        public Object atWithin(ProceedingJoinPoint joinPoint) throws Throwable {
            // childMethod 만
            log.info("[@within] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}