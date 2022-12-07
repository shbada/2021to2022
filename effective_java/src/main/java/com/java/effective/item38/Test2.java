package com.java.effective.item38;

import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.Collection;

public class Test2 {
    public static void main(String[] args) {
        double x = Double.parseDouble("10");
        double y = Double.parseDouble("2");

        test(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    /**
     * 한정적 와일드카드 타입 사용
     * @param opSet
     * @param x
     * @param y
     */
    private static void test(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

//        LinkOption
    }
}
