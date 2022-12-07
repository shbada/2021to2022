package com.java.effective.item32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main2 {
    public static void main(String[] args) {
        String[] attributes = pickTwo("AA", "BB", "CC");
    }

    static <T> T[] toArray(T...args) {
        return args;
    }

    static <T> T[] pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            // Unchecked generics array creation for varargs parameter
            case 0 : return toArray(a,b);
            case 1 : return toArray(a,c);
            case 2 : return toArray(b,c);
        }

        throw new AssertionError(); // 도달할 수 없음
    }

    @SafeVarargs
    static <T> List<T> flatten(List<? extends T>... lists) {
        List<T> result = new ArrayList<>();

        for (List<? extends T> list : lists) {
            result.addAll(list);
        }

        return result;
    }

    static <T> List<T> flatten(List<List<? extends T>> lists) {
        List<T> result = new ArrayList<>();

        for (List<? extends T> list : lists) {
            result.addAll(list);
        }

        return result;
    }
}
