package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.List;

public class _15_배열_리스트_sum {
    public static void main(String[] args) {
        // arr
        int arr[] = new int[] {12,34,45,21,33,4};
        int sum = Arrays.stream(arr).parallel().reduce(0, Integer::sum);
        System.out.println("Array Sum = "+sum);

        int arr2[] = new int[] {12,34,45,21,33,4};
        int sum2 = Arrays.stream(arr2).sum();
        System.out.println("Array Sum = "+sum2);
    }

    private Integer getSum(List<Integer> currentTruckList) {
        return currentTruckList.stream()
                .mapToInt(i -> i)
                .sum();
    }

    private void basicFor() {
        int arr[] = new int[] {12,34,45,21,33,4};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
        }
        System.out.println("Array Sum = "+sum);
    }
}
