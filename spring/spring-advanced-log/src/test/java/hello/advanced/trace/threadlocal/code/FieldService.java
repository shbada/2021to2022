package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldService {
    private String nameStore;

    /**
     * threadA 의 결과값은 threadB 의 수행으로 인해 기대한 결과값이 나오지않는다.
     * 여러 쓰레드가 동시에 같은 인스턴스의 필드(nameStore) 값을 변경하면서 발생하는 문제 = 동시성 문제
     * 트래픽이 점점 많아질수록 자주 발생한다.
     * 스프링 빈 처럼 싱글톤 객체의 필드를 변경하며 사용할때 이러한 동시성 문제를 조심해야한다.
     * @param name
     * @return
     */
    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore);

        /* name 저장 */
        nameStore = name;

        /* 1초 쉰다..*/
        sleep(1000);

        log.info("조회 nameStore={}", nameStore);
        return nameStore;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
