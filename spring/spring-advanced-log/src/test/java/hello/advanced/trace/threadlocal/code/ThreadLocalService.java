package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {
    /**
     * ThreadLocal 클래스 사용
     */
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    /**
     * ThreadLocal get, set method
     * 값 저장: ThreadLocal.set(xxx)
     * 값 조회: ThreadLocal.get()
     * 값 제거: ThreadLocal.remove()
     * @param name
     * @return
     */
    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore.get());

        /* name 저장 */
        nameStore.set(name);

        /* 1초 쉰다..*/
        sleep(1000);

        log.info("조회 nameStore={}", nameStore.get());

        return nameStore.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
