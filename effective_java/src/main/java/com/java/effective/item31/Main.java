package com.java.effective.item31;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static void swap(List<?> list, int i, int j) {
        // list.set(i, list.set(j, list.get(i))); // error
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }
}
