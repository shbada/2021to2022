package ch6.병렬프로그래밍;

import java.util.concurrent.Executor;

public class C02_Executors {
    /**
     * java.util.concurrent 패키지에서 3개의 Executor 인터페이스를 제공한다.
     *
     * 1) Executor
     * = 새로운 태스크를 생성하는데 가장 기본이 되는 인터페이스이다.
     * 2) ExecutorService
     * = Executor 인터페이스의 하위 인터페이스이다. 생명주기 관리하는 기능을 제공한다.
     * 3) ScheduledExecutorService
     * = ExecutorService 인터페이스의 하위 인터페이스이다. 주기적으로 실행되거나 일정 시간 후에 실행할 수 있는 기능을 제공한다.
     */

    /**
     * Executor 인터페이스
     * = execute 메서드 하나만 제공한다. (함수형 인터페이스)
     */

    Thread myThread = new Thread(new Runnable() {
        @Override
        public void run() {

        }
    });

    Executor e = new Executor() {
        @Override
        public void execute(Runnable command) {

        }
    };
}
