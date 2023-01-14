package hello.advanced.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략 패턴
 * 스프링 의존관계 주입에서 사용되는 방식과 동일하다.
 */
@Slf4j
public class ContextV1 {
    private Strategy strategy;

    /**
     * 전략 주입받기
     * @param strategy
     */
    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * strategy.call() 호출
     */
    public void execute() {
        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        strategy.call();
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = {}", resultTime);
    }
}
