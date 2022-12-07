package com.algorithm._06_BinarySearch;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Date 2022/04/15
 * @URL https://www.acmicpc.net/problem/1920
 */
public class A1920_수_찾기 {
    static int N;
    static int M;
    static int[] findArr;
    static int[] arr;

    public static void main(String[] args) {
        // write your code here
        A1920_수_찾기 main = new A1920_수_찾기();
        main.solution();
    }

    public void solution() {
        input();

        binarySearch();
    }

    /**
     * 이진탐색을 사용한 방법
     */
    private static void binarySearch() {
        /* asc sort */
        Arrays.sort(arr);

        for (int target : findArr) {
            /* start, end set */
            int start = 0;
            int end = arr.length - 1;

            boolean isFind = false; // find flag

            while (start <= end) {
                /* 중간점 찾기 */
                int mid = (start + end) / 2;

                if (arr[mid] == target) { /* 찾은 경우 flag true */
                    isFind = true;
                    break;
                } else if (arr[mid] > target) { /* 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인 */
                    end = mid - 1;
                } else {
                    start = mid + 1; /* 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인 */
                }
            }

            System.out.println(isFind ? "1 " : "0");
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        M = sc.nextInt();
        findArr = new int[M];

        for (int i = 0; i < M; i++) {
            findArr[i] = sc.nextInt();
        }
    }
}
