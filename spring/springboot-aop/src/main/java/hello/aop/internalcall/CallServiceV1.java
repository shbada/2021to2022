package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 참고: 생성자 주입은 순환 사이클을 만들기 때문에 실패한다.
 */
@Slf4j
@Component
public class CallServiceV1 {
    private CallServiceV1 callServiceV1;

    /*
        참고로 이 경우 생성자 주입시 오류가 발생한다.
        본인을 생성하면서 주입해야 하기 때문에 순환 사이클이 만들어진다.
        반면에 수정자 주입은 스프링이 생성된 이후에 주입할 수 있기 때문에 오류가 발생하지 않는다


        스프링 부트 2.6부터는 순환 참조를 기본적으로 금지하도록 정책이 변경되었다.
        따라서 이번 예제를 스프링 부트 2.6 이상의 버전에서 실행하면 다음과 같은 오류 메시지가 나오면서 정상 실행되지 않는다.

        spring.main.allow-circular-references=true
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        this.callServiceV1 = callServiceV1;
    }

    public void external() {
        log.info("call external");
        callServiceV1.internal(); // 외부 메서드 호출
    }

    public void internal() {
        log.info("call internal");
    }
}
