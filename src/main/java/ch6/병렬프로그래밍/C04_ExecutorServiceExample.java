package ch6.병렬프로그래밍;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class C04_ExecutorServiceExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new MyTask("TODO 1"));
        executorService.execute(new MyTask("TODO 2"));
        executorService.execute(new MyTask("TODO 3"));

        executorService.shutdown();
    }
}

class MyTask implements Runnable {
    private String id;

    public MyTask(String id) {
        this.id = id;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i ++) {
            System.out.println("ID: " + id + ", running ...." + i);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * newSingleThreadExecutor
 * = 오직 하나의 스레드로 처리하며 나머지 스레드 생성 요청은 현재 스레드가 종료할때까지 대기한다.
 * = 스레드 작업을 안전하게 수행하지만, 여러 개의 스레드를 생성할 수 없다.
 *
 * newFixedThreadPool
 * = 입력 파라미터로 생성할 스레드 풀의 크기를 정의한다.
 * = 스레드 풀의 크기를 넘으면 풀에 여유가 생길때까지 대기한다.
 *
 * newCachedThreadPool
 * = 멀티 스레드 처리를 위한 스레드 풀을 생성하되 기존에 생성한 스레드를 가능한 한 재사용한다.
 * = 멀티 스레드 기반으로 동작하고, 등록된 스레드를 모두 한번에 실행시키며 동시 처리에 대한 개수 제한이 없다.
 *
 * newWorkStealingPool
 * = 스레드 풀을 생성하며, 실행되는 하드웨어의 사용 가능한 모든 프로세서(CPU)의 코어를 쓰도록 병렬 처리 레벨을 설정한다.
 * = 해당 하드웨어의 자원을 모두 선점하려고 하기 때문에 다른 프로세스 혹은 애플리케이션의 성능에 영향이 없다.
 *
 * unconfigurableExecutorService
 * = 메서드의 입력 파라미터로 반드시 ExecutorService 객체를 전달해야한다.
 * = ExecutorService를 구현한 여러 클래스의 기능 중 ExecutorService 의 메서드만 호출하고 나머지 기능을 사용하지 못하도록 제한할 필요가 있을대 사용한다.
 */

