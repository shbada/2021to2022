package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Advisor 1, 2, 3
 * 여러개일때 포인트것의 조건을 모두 만족하면 자동 생성기는 프록시를 '1개' 생성한다.
 * 프록시 팩토리가 생성하는 프록시는 내부에 여러 Advisor 들을 포함할 수 있다.
 * 따라서 프록시를 여러개 생성해서 비용을 낭비할 이유가 없다.
 */
@Configuration
@Slf4j
@Import({AppV1Config.class, AppV2Config.class}) // v3 은 자동으로 빈이 등록되므로 생략
public class AutoProxyConfig {
    /**
     * advisor 만 등록하면 자동으로 된다.
     * 자동 프록시 생성기는 포인트컷을 사용해서 해당 빈이 프록시를 생성할 필요가 있는지 없는지 체크한다.
     * 클래스 + 메서드 조건을 모두 비교한다.
     * 이때 모든 메서드를 체크하고, 조건이 맞는것이 하나라도 있다면 프록시를 생성한다.
     * 조건이 맞는게 하나도 없으면 프록시를 생성할 필요가 없으므로 프록시를 생성하지 않는다.
     * @param logTrace
     * @return
     */
//    @Bean // 아래 advisor2()만 등록되도록
    public Advisor advisor1(LogTrace logTrace) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    //@Bean
    public Advisor advisor2(LogTrace logTrace) {
        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 해당 위치에 있어야 프록시 적용 대상이다.
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public Advisor advisor3(LogTrace logTrace) {
        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // 해당 위치에 있어야 프록시 적용 대상이다.
        // nolog 메서드 제외
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..)");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
