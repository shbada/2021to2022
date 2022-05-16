package com.spring.batch._44_parallel;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class CustomTasklet implements Tasklet {

    private long sum = 0;
    // lock 을 만든다. lock 을 만들지 않고 이 스레드가 가지고있는 lock 을 사용해도 된다.
    // 모든 객체들은 하나의 lock 을 가진다.
    private Object lock = new Object();

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

//        Thread.sleep(1000);

        /**
         * 동기화블록 처리 하지않으면 각 스레드가 동시에 접근하기 때문에 sum이 이상한 값으로 출력된다.
         * 숫자를 10000로 줄여보자.
         * -> 10000, 20000, 30000 정상적으로 출력된다.
         * 100000로 해보자.
         * -> 100000, 200000, 300000 정상적으로 출력된다.
         * 1000000 로 해보자.
         * -> 1000000, 2000000, 3000000 정상적으로 출력된다.
         *
         * 우리가 병렬로 스레드를 실행할때 가장 이슈가 되는건 '동시성'의 문제다.
         * 여러 쓰레드들이 공유 데이터에 접근하게되어, 공유 데이터에 쓰기 작업이 중복적(동시적)으로 발생하게 되면
         * 원하는 값이 나오지 않는다.
         *
         * 1000000000 일때
         * -> 숫자가 높을수록 CPU 연산이 오래걸리기 때문에 찰나의 작은 시간에 정확하지 않은 숫자들이 발생하여 동시성 이슈가 생긴다.
         * 그래서 아래 블록만큼은 하나의 스레드씩 동기화하여 처리되어야한다.
         */
        synchronized (lock) {
            for (int i = 0; i < 1000000000; i++) {
                sum++;
            }
            // thread 이름 출력
            System.out.println(String.format("%s has been executed on thread %s",
                    chunkContext.getStepContext().getStepName(),
                    Thread.currentThread().getName()));
            System.out.println(String.format("sum : %d", sum));
        }

        return RepeatStatus.FINISHED;
    }
}
