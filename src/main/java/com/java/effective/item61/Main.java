package com.java.effective.item61;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> naturalOrder
                = (i, j) -> (i < j) ? -1 : (1 == j ? 0 : 1);

        System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));
    }
}
