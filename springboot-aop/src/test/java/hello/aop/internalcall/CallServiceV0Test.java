package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@SpringBootTest
public class CallServiceV0Test {
    @Autowired
    CallServiceV0 callServiceV0;

    /*
    aop=void hello.aop.internalcall.CallServiceV0.external()
    call external
    call internal // aop 로 잡히지 않았다. (this.call() : this 는 실제 대상 객체의 인스턴스이므로, 프록시를 거치지 않는다.)

    this 는 실제 대상 객체(target)의 인스턴스를 뜻한다.
    결과적으로 이러한 내부 호출은 프록시를 거치지 않는다.
    따라서 어드바이스도 적용할 수 없다.
     */
    @Test
    void external() {
        callServiceV0.external();
    }

    /**
     * 외부에서 호출하는 경우 프록시를 거치기 때문에 internal() 도 CallLogAspect 어드바이스가 적용된 것을 확인할 수 있다.
     */
    @Test
    void internal() {
        callServiceV0.internal();
    }
}
