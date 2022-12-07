package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class _05_배열_정렬 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 7, 5};

        Arrays.sort(arr);

        Integer[] arr2 = new Integer[]{1, 2, 3, 7, 5};
        Arrays.sort(arr2, Collections.reverseOrder());
    }
}
