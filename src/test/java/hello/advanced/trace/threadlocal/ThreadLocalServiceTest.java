package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {
    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");

        /**
         * 동시발생을 위해 쓰레드 생성
         */
        Runnable userA = () -> {
            service.logic("userA");
        };

        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        // sleep(2000); // 2초 쉰다.

        // A 가 완전히 끝나고 B 가 실행됨
        /**
         * 저장 name=userA -> nameStore=null
         * 저장 name=userB -> nameStore=null
         * 조회 nameStore=userA
         * 조회 nameStore=userB
         */
        sleep(100); /** 동시성 문제 발생 하지 않는다. ThreadLocal 사용했기 때문 */
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
