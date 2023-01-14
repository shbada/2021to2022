package org.example.atomic;

import org.example.executor.MyTask;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicExecutorExample {
    public static void main(String[] args) {
        AtomicCounter atomicCounter = new AtomicCounter();
        Counter counter = new Counter();



//        ExecutorService execService = Executors.newSingleThreadExecutor(); // 5
        ExecutorService execService = Executors.newFixedThreadPool(3); // 4
//        ExecutorService execService = Executors.newFixedThreadPool(3); // 4

//        execService.execute(atomicCounter::increment);
//        execService.execute(atomicCounter::increment);
//        execService.execute(atomicCounter::increment);
//        execService.execute(atomicCounter::increment);
//        execService.execute(atomicCounter::increment);
//
//        System.out.println("결과 : " + atomicCounter.value());

        execService.execute(counter::increment);
        execService.execute(counter::increment);
        execService.execute(counter::increment);
        execService.execute(counter::increment);
        execService.execute(counter::increment);

        System.out.println("결과 : " + counter.value());

        // ExecutorService를 종료한다.
        execService.shutdown();
    }
}