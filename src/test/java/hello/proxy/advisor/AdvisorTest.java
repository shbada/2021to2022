package hello.proxy.advisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

public class AdvisorTest {
    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // advisor (pointcut + advice)
        // Pointcut.TRUE : 항상 true 를 반환하는 포인트컷
        DefaultPointcutAdvisor advisor
                = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor); // proxyFactory 에 적용할 어드바이저 지정

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만든 포인트컷")
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        // advisor (pointcut + advice)
        // Pointcut.TRUE : 항상 true 를 반환하는 포인트컷
        DefaultPointcutAdvisor advisor
                = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor); // proxyFactory 에 적용할 어드바이저 지정

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    /**
     * 스프링 제공 Pointcut 인터페이스
     */
    static class MyPointCut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    /**
     * MethodMatcher
     */
    @Slf4j
    static class MyMethodMatcher implements MethodMatcher {

        private String matchName;

        /**
         * 정적
         * @param method
         * @param targetClass
         * @return
         */
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            matchName = "save";
            boolean result = method.getName().equals(matchName); // 메서드명이 save 인 경우에 true

            log.info("포인트컷 호출 method={}, targetClass={}", method.getName(), targetClass);
            log.info("포인트컷 결과 result={}", result);

            return result;
        }

        /**
         * 정적이면(false) 위, 동적이면(true) 아래
         * @return
         */
        @Override
        public boolean isRuntime() {
            return false;
        }

        /**
         * 동적
         * @param method
         * @param targetClass
         * @param args
         * @return
         */
        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
