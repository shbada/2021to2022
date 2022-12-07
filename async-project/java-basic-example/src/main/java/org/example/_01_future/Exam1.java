package org.example._01_future;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exam1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /* Single Thread */
        ExecutorService executor = Executors.newSingleThreadExecutor();

        /* submit() 호출됬을때 Future 객체는 리턴된다. 하지만 값은 아직 설정되지 않았다. */
        Future<Integer> future = executor.submit(() -> {
            System.out.println(LocalTime.now() + " Starting runnable");
            int sum = 1 + 1;
            Thread.sleep(3000);
            System.out.println(LocalTime.now() + " Starting runnable future block");
            return sum;
        });

        /*
            Callable은 인자를 받지 않지만 어떤 데이터를 리턴하는 인터페이스
            Runnable은 인자도 없고 리턴되는 데이터도 없는 인터페이스
         */
//        Future<Integer> future = executor.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                System.out.println(LocalTime.now() + " Starting runnable");
//                int sum = 1 + 1;
//                Thread.sleep(3000);
//                System.out.println(LocalTime.now() + " Starting runnable future block");
//                return sum;
//            }
//        });

        System.out.println(LocalTime.now() + " Waiting the task done");

        /* Future 에 값이 셋팅될때까지 기다린다. Callable 이 어떤 값을 리턴하면 그 값을 Future 에 설정한다. */
        Integer result = future.get();
        System.out.println(LocalTime.now() + " Result : " + result);
    }
}
