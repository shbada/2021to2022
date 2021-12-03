package com.java.effective.item61;

import java.util.Comparator;

public class Main2 {
    public static void main(String[] args) {
        Comparator<Integer> naturalOrder = (iBoxed, jBoxed) -> {
            int i = iBoxed;
            int j = jBoxed; // 오토박싱
            return i < j ? -1 : (i == j ? 0 : 1);
        };

        System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
    }
}
