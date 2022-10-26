package org.example._01_future;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class Exam2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println(LocalTime.now() + " Doing something");
            Integer sum = 1 + 1;
            Thread.sleep(3000);

            /* 다른 쓰레드로 전달할 데이터를 저장 */
            future.complete(sum);
            return null;
        });

        System.out.println(LocalTime.now() + " Waiting the task done");

        /* 데이터가 set 될때까지 대기 */
        Integer result = future.get();
        System.out.println(LocalTime.now() + " Result : " + result);
    }
}
