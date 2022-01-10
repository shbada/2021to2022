package hello.advanced.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 자식클래스는 부모클래스의 로직을 사용하지 않는다.
 * 하지만 상속해서 받고있따.
 * 자식클래스가 부모클래스를 의존하게되고, 부모클래스에 수정이 발생하면 자식클래스도 변경이 필요하다.
 */
@Slf4j
public class SubClassLogic1 extends AbstractTemplate {
    /**
     * 변하는 부분인 비즈니스 로직1을 처리하는 자식 클래스이다.
     * 템플릿이 호출하는 대상인 call() 메서드를 오버라이딩 한다.
     */
    @Override
    protected void call() {
        log.info("비즈니스 로직1 실행");
    }
}
