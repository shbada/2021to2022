package com.algorithm._01_그리디_구현;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Date 2022/04/14
 * @URL https://www.acmicpc.net/problem/11399
 */
public class A11399_ATM {
    static int N;
    static int arr[];
    static int result[];

    public static void main(String[] args) {
        // write your code here
        A11399_ATM main = new A11399_ATM();
        main.solution();
    }

    public void solution() {
        input();

        // 오름차순 정렬
        Arrays.sort(arr);

        /* 0번째 셋팅 */
        result[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            /* result update (이전의 총 합 + 현재의 타겟) */
            result[i] = result[i - 1] + arr[i];
        }

        int sum = Arrays.stream(result).sum();
        System.out.println(sum);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N];
        result = new int[N];

        /* 인접행렬 생성 */
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
    }
}
