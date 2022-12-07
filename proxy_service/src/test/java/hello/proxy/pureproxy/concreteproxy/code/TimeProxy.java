package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic {
    // 프록시니까 실제 호출한 대상이 필요
    private ConcreteLogic concreteLogic; // 실제 객체

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        log.info("TimeProxy 실행");

        long startTime = System.currentTimeMillis();

        // proxy -> proxy
        String result = concreteLogic.operation();

        long endTime = System.currentTimeMillis();

        // 특정시간 측정 (부가기능) 데코레이션 로직 추가
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTIme = {}ms", resultTime);

        return result;
    }
}
