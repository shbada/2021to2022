package org.example.chap02_multiThread._step09_parallel_exam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * 또다른 예제
 * This class adds the sum of the assigned partition of the array.
 *
 * 각 블록사이즈별의 합 구하기
 * 3일 경우, index 0 ~ 2, 3 ~ 5, 6 ~ 9
 */
class SumCalculatorTask implements Callable<Integer> {

    private int arr[];
    private int start;
    private int end;

    public SumCalculatorTask(int arr[], int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    /*
     * Calculate the sum of the elements of the given partition
     */
    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }

        return sum;
    }

}

public class SumCalculatorTaskMain {

    public static void main(String[] args) {
        // 배열 선언
        int arr[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};

        // Size of each block.
        int blockSize = 3;

        int noOfBlocks = (int) Math.ceil((double) arr.length / blockSize);

        // A fixed thread pool of size 3 to calculate the sum.
        ExecutorService executorService =
                Executors.newFixedThreadPool(3);

        // Future objects
        List<Future<Integer>> futureObjList =
                new ArrayList<Future<Integer>>();

        // For each partition.
        int start = 0, end;
        for (int i = 1; i <= noOfBlocks; i++) {

            start = (i-1) * blockSize;
            end = start + blockSize - 1;

            if (end >= arr.length) {
                end = arr.length - 1;
            }

            Future<Integer> future =
                    executorService.submit(new SumCalculatorTask(arr, start, end));

            futureObjList.add(future);
        }

        int totalSum = 0;
        for (Future<Integer> future : futureObjList) {
            // 결과 얻기
            totalSum += getComputedValue(future);
        }

        // 최종결과
        System.out.println("Final Sum " + totalSum);

    }

    private static int getComputedValue(Future<Integer> future) {

        while (! future.isDone()) {
            Thread.yield();
        }

        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0; // ignore
        }
    }

}
