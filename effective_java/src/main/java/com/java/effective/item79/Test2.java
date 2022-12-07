package com.java.effective.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 {
    /**
     * 쓸데없는 백그라운드 스레드를 사용하는 관찰자
     * @param args
     */
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);
                if (e == 23) {
                    ExecutorService exec = Executors.newSingleThreadExecutor();

                    try {
                        // 관찰자를 잠그려 시도 (락을 얻을 수 없다. 이미 main 스레드가 락을 쥐고있다.
                        // main 스레드는 백그라운드 스레드가 관찰자를 제거하기만을 기다리고있다.
                        // -> 교착상태
                        exec.submit(() -> s.removeObserver(this)).get();
                    } catch (ExecutionException | InterruptedException ex) {
                        throw new AssertionError(ex);
                    } finally {
                        exec.shutdown();
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
