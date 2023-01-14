package hello.aop.pointcut;

import hello.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

/**
 * this, target
 * 적용 타입 하나를 정확하게 지정해야한다.
 * 같은패턴을사용할수없다.
 * 부모 타입을 허용한다.
 *
 * this 는 스프링 빈으로 등록되어 있는 프록시 객체를 대상으로 포인트컷을 매칭한다.
 * target 은 실제 target 객체를 대상으로 포인트컷을 매칭한다.
 *
 * 프록시를 대상으로 하는 this 의 경우 구체 클래스를 지정하면
 * 프록시 생성 전략에 따라서 다른 결과가 나올 수 있다는 점을 알아두자.
 *
 * JDK 동적 프록시: 인터페이스가 필수이고, 인터페이스를 구현한 프록시 객체를 생성한다.
 * CGLIB: 인터페이스가 있어도 구체 클래스를 상속 받아서 프록시 객체를 생성한다
 */

/**
 * application.properties
 * spring.aop.proxy-target-class=true CGLIB
 * spring.aop.proxy-target-class=false JDK 동적 프록시
 */
@Slf4j
@Import(ThisTargetTest.ThisTargetAspect.class)
@SpringBootTest(properties = "spring.aop.proxy-target-class=false") //JDK 동적 프록시
//@SpringBootTest(properties = "spring.aop.proxy-target-class=true") //CGLIB (스프링부트 default)
public class ThisTargetTest {
    @Autowired
    MemberService memberService;

    @Test
    void success() {
        // [target-impl] String hello.aop.member.MemberService.hello(String)
        // [target-interface] String hello.aop.member.MemberService.hello(String)
        // [this-interface] String hello.aop.member.MemberService.hello(String)

        // JDK 동적 프록시
        // log.info("[this-impl] {}", joinPoint.getSignature()); -> 얘가 안됨
        // -> JDK 동적 프록시에서 MemberServiceImpl 의 부모클래스를 모른다. 그러므로 적용이 안된다.

        // CGLIB
        // 모두 해당한다.
        // CGLIB 는 MemberServiceImpl 프록시를 상속해서 만들기 때문에 부모타입을 알수있다.
        log.info("memberService Proxy={}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ThisTargetAspect {
        // 부모 타입 허용
        @Around("this(hello.aop.member.MemberService)")
        public Object doThisInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // 부모 타입 허용
        @Around("target(hello.aop.member.MemberService)")
        public Object doTargetInterface(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-interface] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // this: 스프링 AOP 프록시 객체 대상
        // JDK 동적 프록시는 인터페이스를 기반으로 생성되므로 구현 클래스를 알 수 없음
        // CGLIB 프록시는 구현 클래스를 기반으로 생성되므로 구현 클래스를 알 수 있음
        @Around("this(hello.aop.member.MemberServiceImpl)")
        public Object doThis(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[this-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // target: 실제 target 객체 대상
        @Around("target(hello.aop.member.MemberServiceImpl)")
        public Object doTarget(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[target-impl] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }
}
