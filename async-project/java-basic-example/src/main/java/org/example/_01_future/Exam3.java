package org.example._01_future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Exam3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<String> dataReadFuture = executorService.submit(new DataReader());
        Future<String> dataProcessFuture = executorService.submit(new DataProcessor());

        System.out.println("isDone()");

        while (!dataReadFuture.isDone() && !dataProcessFuture.isDone()) {
            System.out.println("Reading and processing not yet finished.");
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("get()");

        System.out.println(dataReadFuture.get());
        System.out.println(dataProcessFuture.get());
    }
}

class DataReader implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Reading data...");
        TimeUnit.SECONDS.sleep(5);
        return "Data reading finished";
    }
}

class DataProcessor implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Processing data...");
        TimeUnit.SECONDS.sleep(5);
        return "Data is processed";
    }
}