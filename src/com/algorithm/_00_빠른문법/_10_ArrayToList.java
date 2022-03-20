package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _10_ArrayToList {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 6, 2, 3};

        /* Array to List */
        List<Integer> intList = Arrays.stream(arr).boxed().collect(Collectors.toList());
    }
}
