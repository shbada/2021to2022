package com.java.effective.item20;

public class TestSub2 extends AbstractTest implements Test {
    @Override
    public void set(int value) {
        System.out.println("set2 : " + value);
    }
}
