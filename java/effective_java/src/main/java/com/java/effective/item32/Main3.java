package com.java.effective.item32;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main3 {
    public static void main(String[] args) {
        List<String> attributes = pickTwo("AA", "BB", "CC");
    }

    static <T> T[] toArray(T...args) {
        return args;
    }

    static <T> List<T> pickTwo(T a, T b, T c) {
        switch(ThreadLocalRandom.current().nextInt(3)) {
            // Unchecked generics array creation for varargs parameter
            case 0 : return List.of(a,b);
            case 1 : return List.of(a,c);
            case 2 : return List.of(b,c);
        }

        throw new AssertionError(); // 도달할 수 없음
    }
}
