package com.java.effective.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public class Time {
    /**
     * concurrency 매개변수로 지정한 지정한 동시성 수준만큼의 스레드를 생성할 수 있어야한다.
     * 그렇지못하면 이 메서드는 결코 끝나지 않게된다. (스레드 기아 교착상태)
     * @param executor ;실행자
     * @param concurrency
     * @param action
     * @return
     * @throws InterruptedException
     */
    public static long time(Executor executor, int concurrency,
                            Runnable action) throws InterruptedException {
        // 작업자 스레드들이 준비가 완료됐음을 타이머 스레드에 통지할때 사용
        CountDownLatch ready = new CountDownLatch(concurrency);

        // 모든 스레드가 동작 준비가 완료되고 작업을 들어가게 하기 위한 래치
        // 통지를 끝낸 작업자 스레드들은 두번째 래치인 start가 열리기를 기다린다.
        CountDownLatch start = new CountDownLatch(1);

        // 모든 스레드가 동작을 완료한 순간을 위한 래치
        /*
            마지막 작업자 스레드가 reday.countDown을 호출하면 타이머 스레드가 시작 시간을 기록한다.
            start.countDown이 호출하여 기다리던 작업자 스레드들을 깨운다. 그 직후 타이머 스레드는 세번째 래치인 done이 열리기를 기다린다.
            done 래치는 마지막 남은 작업자 스레드가 동작을 마치고 done.countDown을 호출하면 열린다.
            타이머 스레드는 done 래치가 열리자마자 깨어나 종료 시각을 기록한다.
         */
        CountDownLatch done  = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                // 타이머에게 준비를 마쳤음을 알린다.
                ready.countDown();
                try {
                    // 모든 작업자 스레드가 준비될 때까지 기다린다.
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    // 인터럽트를 되살리고 자신은 run 메서드에서 빠져나온다.
                    Thread.currentThread().interrupt();
                } finally {
                    // 타이머에게 작업을 마쳤음을 알린다.
                    done.countDown();
                }
            });
        }

        // 모든 작업자가 준비될 때까지 기다린다.
        ready.await();

        /*
            시간을 잴때는 System.nanoTime()를 사용하자.
            System.currentTimeMillis보다 더 정확하고 정밀하며 시스템의 실시간 시계의 시간 보정에 영향받지 않는다.
         */
        long startNanos = System.nanoTime();

        // 작업자들을 깨운다.
        start.countDown();

        // 모든 작업이 종료될 떄까지 대기한다.
        done.await();

        return System.nanoTime() - startNanos;
    }
}
