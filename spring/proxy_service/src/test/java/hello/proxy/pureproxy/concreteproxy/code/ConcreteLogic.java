package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 구체 클래스
 */
@Slf4j
public class ConcreteLogic {
    public String operation() {
        log.info("ConcreteLogic 실행");
        return "data";
    }
}
