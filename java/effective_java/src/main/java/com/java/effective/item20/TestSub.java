package com.java.effective.item20;

public class TestSub extends AbstractTest implements Test {
    @Override
    public void set(int value) {
        System.out.println("set : " + value);
    }
}
