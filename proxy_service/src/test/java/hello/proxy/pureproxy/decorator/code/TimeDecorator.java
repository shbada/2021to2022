package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {
    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        long startTime = System.currentTimeMillis();

        // proxy -> proxy
        String result = component.operation();

        long endTime = System.currentTimeMillis();

        // 특정시간 측정 (부가기능) 데코레이션 로직 추가
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTIme = {}ms", resultTime);

        return result;
    }
}
