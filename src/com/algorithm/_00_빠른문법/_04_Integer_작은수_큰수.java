package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _04_Integer_작은수_큰수 {
    public static void main(String[] args) {
        /* 작은 수 */
        int maxValue = Integer.MIN_VALUE;

        /* 큰 수 */
        int minValue = Integer.MAX_VALUE;

        Scanner sc = new Scanner(System.in);
        int n = 100;
        int m = 50;
        int result = 0;

        for (int i = 0; i < n; i++) {
            // 현재 줄에서 '가장 작은 수' 찾기
            int min_value = 10001;
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                min_value = Math.min(min_value, x);
            }

            // '가장 작은 수'들 중에서 가장 큰 수 찾기
            result = Math.max(result, min_value);
        }
    }
}
