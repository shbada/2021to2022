package hello.advanced.app.v1;

import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository // component scan 대상
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId) {
        // 저장 로직
//        if (itemId.equals("ex")) {
//            throw new IllegalStateException("예외 발생!");
//        }

        TraceStatus status = null;

        try {
            status = trace.begin("OrderRepository.save()"); //저장 로직

            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }

            sleep(1000);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e; }

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
