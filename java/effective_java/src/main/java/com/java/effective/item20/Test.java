package com.java.effective.item20;

public interface Test {
    public void get(int value);
    public void set(int value);

    default void remove(int value) {
        System.out.println("remove : " + value);
    }
}
