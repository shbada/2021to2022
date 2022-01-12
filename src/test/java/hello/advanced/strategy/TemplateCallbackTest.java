package hello.advanced.strategy;

import hello.advanced.strategy.code.templatecallback.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {
    /**
     * 템플릿 콜백 패턴
     */
    @Test
    void callbackV1() {
        TimeLogTemplate template = new TimeLogTemplate();

        // 실행코드는 execute()가 실행되는 중에 call() 로 호출된다.
        template.execute(() -> log.info("비즈니스 로직1 실행"));
        template.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
