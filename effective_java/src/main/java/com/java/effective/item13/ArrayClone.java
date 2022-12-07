package com.java.effective.item13;

import java.util.Arrays;

/**
 * 배열의 복사 (clone 메서드)
 */
public class ArrayClone {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 5};

        /* 얕은 복사 */
        int[] copyArr = arr;

        copyArr[2] = 6;
        /* arr 도 함께 변경된다. */
        System.out.println(Arrays.toString(arr)); // [1, 3, 6, 5]
        System.out.println(Arrays.toString(copyArr)); // [1, 3, 6, 5]

        /* 깊은 복사 */
        int[] cloneArr = arr.clone();

        cloneArr[2] = 9;
        /* arr 은 변경되지 않는다. */
        System.out.println(Arrays.toString(arr)); // [1, 3, 6, 5]
        System.out.println(Arrays.toString(cloneArr)); // [1, 3, 9, 5]
    }
}
