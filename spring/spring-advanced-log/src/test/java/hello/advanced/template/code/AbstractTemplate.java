package hello.advanced.template.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 템플릿 메서드 패턴
 * 템플릿은 기준이 되는 거대한 틀이다.
 * 템플릿이라는 틀에 변하지 않는 부분을 몰아두고, 그 안에서 일부 변하는 부분을 별도로 호출해서 해결한다.
 *
 * call() : 변하는 부분으로 별도로 호출로 변경하였다.
 * 템플릿 메서드 패턴은 부모 클래스에 변하지 않는 템플릿 코드를 준다.
 * 그리고 변하는 부분은 자식 클래스에 두고 상속고 오버라이딩을 사용해서 처리한다.
 */
@Slf4j
public abstract class AbstractTemplate {
    public void execute() {
        long startTime = System.currentTimeMillis();

        /**
         * 템플릿 메서드 패턴
         */
        /* 변하는 부분 */
        call();
        // 비즈니스 로직 실행
//        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("resultTime = {}", resultTime);
    }

    protected abstract void call();
}
