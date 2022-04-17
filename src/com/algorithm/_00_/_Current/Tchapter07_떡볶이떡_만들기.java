package com.algorithm._00_._Current;

import java.util.Arrays;
import java.util.Scanner;

/*
   떡을 절단길로 잘라서 맞춘다.
   높이 H
   19 14 10 17  H 를 15로 지정하면
    4  0  0  2 가 남는다. -> 손님은 총 6cm 만큼의 떡을 가져간다.
    요청한 길이가 M 일때 적어도 M 만큼의 떡을 얻기위해 절단기에 설정할 수 있는 높이의 최대값 구하기
    떡의개수 4, 길이 6
    19 15 10 17
    15 15 15 15 : H
    4  0  0   2   -> 길이 6이 된다. 그러므로 H = 15
 */
public class Tchapter07_떡볶이떡_만들기 {
    static int N; // 떡 개수
    static int M; // 손님이 요청한 떡의 길이
    // static int H; // 결과 (자를 높이)

    static int[] arr; // 떡 배열

    public static void main(String[] args) {
        input();

        binarySearch();
    }

    /**
     4 6
     19 15 10 17
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];

        /* 부품 존재 배열 */
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
    }

    /**
     * 이진탐색
     */
    private static void binarySearch() {
        /* asc sort */
        Arrays.sort(arr);

        /* start, end set */
        int start = 0;
        int end = arr[arr.length - 1];

        // int sum = 0;
        int result = 0;

        while (start <= end) {
            /* 중간점 찾기 */
            int mid = (start + end) / 2;
            // H = arr[mid];

            int sum = 0;

            for (int j : arr) {
                // value += Math.max(j - H, 0);

                /* mid 보다 클 경우에만 처리 */
                if (j > mid) {
                    sum += j - mid;
                }
            }

            if (sum < M) { /* value 가 요청한 떡의 길이보다 작으면 end 를 줄여서 더 작게 */
                end = mid - 1;
            } else {
                result = mid; /* 최대한 덜 잘랐을때 */
                start = mid + 1; /* value 가 요청한 떡의 길이보다 작으면 start 를 늘려서 더 크게 */
            }

        }

        System.out.println(result);
    }
}
