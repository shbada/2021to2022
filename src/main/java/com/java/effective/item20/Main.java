package com.java.effective.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

/**
 * https://devfunny.tistory.com/551
 */
public class Main {
    public static void main(String[] args) {
        TestSub testSub = new TestSub();
        testSub.get(1); // get : 1
        testSub.set(2); // set : 2
        testSub.remove(2); // remove : 2

        TestSub2 testSub2 = new TestSub2();
        testSub2.get(1); // get : 1
        testSub2.set(2); // set2 : 2
        testSub2.remove(2); // remove : 2
    }

    public static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index]; // 오토박싱
            }

            @Override
            public Integer set(int i, Integer val) {
               int oldVal = a[i];
               a[i] = val; // 오토 언박싱
               return oldVal; // 오토박싱
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
