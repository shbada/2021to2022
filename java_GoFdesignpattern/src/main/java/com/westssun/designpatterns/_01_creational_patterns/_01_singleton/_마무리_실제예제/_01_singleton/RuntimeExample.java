package com.westssun.designpatterns._01_creational_patterns._01_singleton._마무리_실제예제._01_singleton;

public class RuntimeExample {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.freeMemory());
    }
}
