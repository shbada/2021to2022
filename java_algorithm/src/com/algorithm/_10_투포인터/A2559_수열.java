package com.algorithm._10_투포인터;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/2559
 */
public class A2559_수열 {
    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) {
        // write your code here
        A2559_수열 main = new A2559_수열();
        main.solution();
    }

    public void solution() {
        input();

        /* 처음 k개만큼 합 구하기 */
        int currentSum = IntStream.rangeClosed(1, K)
                .map(i -> arr[i])
                .sum();

        /* 투포인터 로직 수행 */
        int max = currentSum;
        // K + 1 인덱스부터 시작
        // K = 5일때) 위에서 1 ~ 5 까지의 합을 구했고, 그 다음은 2 ~ 6 이므로 (K + 1) 인 6으로 설정한다.
        for (int i = K + 1; i < arr.length; i++) {
            // 현재 step 에서 더해질 마지막 end index 를 더한다.
            // 이전에 구한 1 ~ 5 까지의 합에서 더하고,
            currentSum += arr[i];

            // i 가 6일때 K = 5, 즉, 1 ~ 5까지에서 1에 해당하는 start index 를 뺀다.
            currentSum -= arr[i - K];

            // 최댓값 설정
            max = Math.max(max, currentSum);
        }

        System.out.println(max);

    }

    /** 시간초과 발생 */
//    public void solution() {
//        input();
//
//        int cnt = 0;
//
//        int start = 1;
//        int end = K;
//        int max = Integer.MIN_VALUE;
//
//        int totalCnt = 0;
//
//        int currentSum = 0;
//
//        while (end < arr.length) {
//            if (cnt == K) {
//                // result 셋팅
//                totalCnt++;
//                max = Math.max(max, currentSum);
//
//                currentSum = 0;
//                cnt = 0; // 리셋
//
//                start = totalCnt + 1; // 1부터 시작이므로
//            } else {
//                currentSum += arr[start];
//
//                if (start == end) {
//                    end++;
//                }
//
//                start++;
//
//                cnt++; // K 개를 더해야한다.
//            }
//        }
//
//        System.out.println(max);
//    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        arr = new int[N + 1]; // 1부터 시작

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
    }
}
