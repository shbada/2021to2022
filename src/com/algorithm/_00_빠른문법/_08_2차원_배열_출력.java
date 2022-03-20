package com.algorithm._00_빠른문법;

import java.util.Arrays;

public class _08_2차원_배열_출력 {
    public static void main(String[] args) {
        int[][] arr1 = new int[2][1];
        arr1[0][0] = 1;
        arr1[1][0] = 2;
        System.out.println(Arrays.deepToString(arr1));

        int[][] arr2 = new int[2][1];
        arr2[0][0] = 3;
        arr2[1][0] = 4;

        System.out.println(Arrays.deepToString(arr2));
    }
}
