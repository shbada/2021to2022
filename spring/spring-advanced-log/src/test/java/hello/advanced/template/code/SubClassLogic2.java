package hello.advanced.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic2 extends AbstractTemplate {
    /**
     * 변하는 부분인 비즈니스 로직1을 처리하는 자식 클래스이다.
     * 템플릿이 호출하는 대상인 call() 메서드를 오버라이딩 한다.
     */
    @Override
    protected void call() {
        log.info("비즈니스 로직2 실행");
    }
}
