package com.westssun.designpatterns._02_structural_patterns._11_flyweight._03_java;

public class FlyweightInJava {

    public static void main(String[] args) {
        /**
         *  valueOf(value) : 캐싱함
         *  범위 안에 속한다면 항상 캐시 처리함
         */
        Integer i1 = Integer.valueOf(10);
        Integer i2 = Integer.valueOf(10);
        System.out.println(i1 == i2); // true
        System.out.println(i1.equals(i2)); // true
    }
}
