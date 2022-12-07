package com.java.effective.item53;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(sum(1, 2, 3));
    }

    static int sum(int... args) {
        return Arrays.stream(args).sum();
    }

    /**
     * 인수가 0 개이면 컴파일 타임이 아닌 런타임에 알 수 있다.
     * @param args
     * @return
     */
    static int min(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
        }

        int min = args[0];

        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }
        return min;
    }

    static int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }

        return min;
    }
}
