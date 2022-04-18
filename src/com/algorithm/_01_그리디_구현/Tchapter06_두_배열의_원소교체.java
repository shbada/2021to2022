package com.algorithm._01_그리디_구현;

import java.util.Arrays;
import java.util.Scanner;

import java.util.Collections;

/*
   배열 A, 배열 B
   배열 A의 원소를 최대 K번 배열 B와 변환하여 배열 A의 합이 최대가 되도록한다.
   -> 즉, B의 최대값과 A의 최소값을 변경해야한다.
 */
public class Tchapter06_두_배열의_원소교체 {
    static int N; /* 배열 길이 */
    static int K; /* 변환할 수 있는 최대 횟수 */
    static int[] arrA;
    static Integer[] arrB;

    /**
     * 입력받기
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        arrA = new int[N];
        arrB = new Integer[N];

        /* 배열 A */
        for (int i = 0; i < N; i++) {
            arrA[i] = sc.nextInt();
        }

        /* 배열 B */
        for (int i = 0; i < N; i++) {
            arrB[i] = sc.nextInt();
        }
    }

    public static void main(String[] args) {
        input();

        /* arrA 오름차순 정렬 */
        Arrays.sort(arrA);

        /* arrB 내림차순 정렬 */
        Arrays.sort(arrB, Collections.reverseOrder());

        int index = 0;
        while (K > 0) {
            /* arrA 원소가 더 작은 경우 */
            if (arrA[index] < arrB[index]) {
                /* swap */
                int temp = arrA[index];
                arrA[index] = arrB[index];
                arrB[index] = temp;
            } else {
                /* 반복문 탈출
                 - arrA[index] > arrB[index] 의 경우를 보자.
                   arrA는 오름차순, arrB는 내림차순이므로
                   arrA가 arrB 원소보다 큰 시점을 만났다면, 그 이후로는 swap 할 필요가 없다.
                   swap 하게되면 arrA 의 합이 더 작아지기 때문이다.
                 */
                break;
            }

            /* 횟수 차감 */
            K = K - 1;

            /* 인덱스 증가 */
            index = index + 1;
        }

        System.out.println(Arrays.stream(arrA).sum());
    }

}
