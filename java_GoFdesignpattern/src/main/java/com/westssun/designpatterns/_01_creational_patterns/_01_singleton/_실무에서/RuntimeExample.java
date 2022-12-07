package com.westssun.designpatterns._01_creational_patterns._01_singleton._실무에서;

public class RuntimeExample {
    public static void main(String[] args) {
        // 오로지 getRuntime() 을 통해서만 만들 수 있다.
        Runtime runtime = Runtime.getRuntime();

        System.out.println(runtime.maxMemory());
        System.out.println(runtime.freeMemory());
    }
}
