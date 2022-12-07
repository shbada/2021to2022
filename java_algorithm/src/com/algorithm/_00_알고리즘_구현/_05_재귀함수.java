package com.algorithm._00_알고리즘_구현;

/**
 * Recursive
 */
public class _05_재귀함수 {
    public static void main(String[] args) {
        recursiveFunction();
    }

    private static void recursiveFunction() {
        System.out.println("재귀함수 호출");
        recursiveFunction();
    }

    private static void recursiveFunction2(int i) {
        if (i == 100) {
            return;
        }

        System.out.println(i + "번째 재귀 함수에서 " + (i + 1) + "번째 재귀함수를 호출합니다.");

        recursiveFunction2(i + 1);

        System.out.println(i + "번째 재귀함수를 종료합니다.");
    }
}
