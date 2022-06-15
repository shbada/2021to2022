package org.example.future;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureExample {
    // 제곱을 계산하는 Callable 객체를 생성한다.
    public Callable<Long> calSquare(long value) {
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Long returnValue = value * value;
                TimeUnit.SECONDS.sleep(1);

                System.out.println(value + "의 제곱근은 " + returnValue);

                return returnValue;
            }
        };

        return callable;
    }

    public void executeTest() {
        List<Long> sampleDataList = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L);
        List<Future<Long>> futureList = new ArrayList<>();

        // 쓰레드 풀을 생성한다. (고정 스레드 풀 이용)
        ExecutorService servicePool = Executors.newFixedThreadPool(4);

        // Callable 객체를 생성한 후 쓰레드 풀에 등록한다. 등록된 쓰레드에 대해 Future 객체를 리턴 받는다.
        for(Long sampleValue : sampleDataList) {
            /*
               1) Runnable : 메서드가 void형인 run 메서드만 있다.
               2) Callable : 제네릭으로 정의한 리턴 타입을 가지는 call 메서드가 제공된다.
               (비동기로 데이터를 처리한 이후에 그 결과를 리턴할 필요가 있다면 Callable을 이용해야한다.)
             */
            Future<Long> future = servicePool.submit(calSquare(sampleValue));
            futureList.add(future);
        }

        Long sumValue = 0L;

        // Future 목록의 결과를 확인한다.
        for(Future<Long> future : futureList) {
            try {
                // 결과를 읽어들일 때가지 대기한다.
                // 대기 하는 동안, 쓰레드가 계산을 하고 값을 리턴한다.
                sumValue += future.get();
            }
            catch(ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("최종 합계 : " + sumValue);

        servicePool.shutdown();
    }

    public static void main(String[] args) {
        FutureExample futureExample = new FutureExample();
        futureExample.executeTest();
    }
}
