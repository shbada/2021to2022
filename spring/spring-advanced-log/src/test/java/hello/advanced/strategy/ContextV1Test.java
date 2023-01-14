package hello.advanced.strategy;

import hello.advanced.strategy.code.strategy.ContextV1;
import hello.advanced.strategy.code.strategy.Strategy;
import hello.advanced.strategy.code.strategy.StrategyLogic1;
import hello.advanced.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    /**
     * logic1(), logic2()
     * 변하는 부분 : 비즈니스 로직
     * 변하지 않는 부분 : 시간 측정 로직
     *
     * Strategy : 변하는 로직 담당
     * Context : 변하지 않는 템플릿 역할
     *
     * Context (execute()) -> Strategy(Interface-call()) <-> 구현체 StrategyLogic1, StrategyLogic2
     */
    private void logic1() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = {}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = {}", resultTime);
    }

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();
    }

    @Test
    void strategyV2() {
        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    /**
     *전략 패턴 익명 내부 클래스1
     */
    @Test
    void strategyV2_func() {
//        Strategy strategyLogic1 = new Strategy() {
//            @Override
//            public void call() {
//                log.info("비즈니스 로직1 실행");
//            }
//        };
//
//        log.info("strategyLogic1={}", strategyLogic1.getClass());
//
//        ContextV1 context1 = new ContextV1(strategyLogic1);
//        context1.execute();
//
//        Strategy strategyLogic2 = new Strategy() {
//            @Override
//            public void call() {
//                log.info("비즈니스 로직2 실행");
//            }
//        };
//
//        log.info("strategyLogic2={}", strategyLogic2.getClass());
//
//        ContextV1 context2 = new ContextV1(strategyLogic2);
//        context2.execute();

//        ContextV1 context1 = new ContextV1(new Strategy() {
//            @Override
//            public void call() {
//                log.info("비즈니스 로직1 실행");
//            }
//        });
        /**
         * Strategy 가 하나기 때문에 람다식을 사용할 수 있다.
         */
        ContextV1 context1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        context1.execute();

//        ContextV1 context2 = new ContextV1(new Strategy() {
//            @Override
//            public void call() {
//                log.info("비즈니스 로직2 실행");
//            }
//        });
        ContextV1 context2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        context2.execute();

    }
}
