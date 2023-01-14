package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");

        /**
         * 동시발생을 위해 쓰레드 생성
         */
        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        // sleep(2000); // 2초 쉰다.

        // A 가 완전히 끝나고 B 가 실행됨
        sleep(100); /** 동시성 문제 발생 */
        threadB.start();

        // 위 스레드가 돌고있는데 main 이 끝나버린다.
        // 이를 위해 아래 코드를 넣자.
        sleep(3000); // 더 좋은 코드가 있지만 테스트를 위해 간단히 2초만 더 쉬자.

        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
