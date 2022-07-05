package com.algorithm._01_그리디_구현;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @Date 2022/07/05
 * @URL https://www.acmicpc.net/problem/18406
 */
public class A18406_럭키_스트레이트 {
    public static void main(String[] args) {
        A18406_럭키_스트레이트 main = new A18406_럭키_스트레이트();
        System.out.println(main.solution());
        System.out.println(main.solution2());
    }

    public String solution() {
        Scanner sc = new Scanner(System.in);
        String point = sc.next();

        int size = point.length();

        /* 왼쪽 합 */
        int leftSum = IntStream.range(0, size / 2)
                .map(i -> (int) point.charAt(i)).sum();

        /* 오른쪽 합 */
        int rightSum = IntStream.range(size / 2, size)
                .map(i -> (int) point.charAt(i)).sum();

        return leftSum == rightSum ? "LUCKY" : "READY";
    }

    public String solution2() {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        char[] arr = s.toCharArray();
        String result = "READY";

        int a = 0;
        int b = 0;

        for (int i = 0; i < arr.length / 2; i++) {
            a += arr[i] - '0';
            b += arr[arr.length - 1 - i] - '0';

            System.out.println("--");
        }

        if (a == b) {
            result = "LUCKY";
        }

        return result;
    }
}
