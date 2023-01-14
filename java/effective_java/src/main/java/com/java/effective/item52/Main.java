package com.java.effective.item52;

import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // ObjectOutputStream

        // 1번. Thread의 생성자 호출
        new Thread(System.out::println).start();

        // 2번. ExecutorService의 submit 메서드 호출
        ExecutorService exec = Executors.newCachedThreadPool();
        // exec.submit(System.out::println);
    }
}
