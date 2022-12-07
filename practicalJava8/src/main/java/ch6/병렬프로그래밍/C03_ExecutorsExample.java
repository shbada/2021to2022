package ch6.병렬프로그래밍;

import java.util.concurrent.Executor;

public class C03_ExecutorsExample implements Executor {

    @Override
    public void execute(Runnable command) {
        // 1) Runnable 인터페이스를 직접 실행한다.
        command.run();

        // 2) Thread를 생성해서 실행한다.
        // new Thread(task).start();
    }

    public static void main(String[] args) {
        Executor executor = new C03_ExecutorsExample();
        executor.execute(() -> System.out.println("Hello, Executor!!"));
    }


}
