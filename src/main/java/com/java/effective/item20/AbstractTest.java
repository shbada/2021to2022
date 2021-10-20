package com.java.effective.item20;

/**
 * 추상 골격 구현 클래스
 */
public abstract class AbstractTest implements Test {
    @Override
    public void get(int value) {
        System.out.println("get : " + value);
    }
}
