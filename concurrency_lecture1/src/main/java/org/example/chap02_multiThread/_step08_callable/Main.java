package org.example.chap02_multiThread._step08_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class MyMath {
    public static int add(int a, int b) {
        return a + b;
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        int x = 10;
        int y = 20;

        ExecutorService executor =
                Executors.newFixedThreadPool(1);

        Future<Integer> future =
                executor.submit(
                        new Callable<Integer>() {
                            public Integer call() {
                                return MyMath.add(x, y);
                            }
                        });

        while( ! future.isDone())
            ; // wait

        int z = future.get();

        System.out.println( "Result is " + z );
    }
}