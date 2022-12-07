package com.algorithm._07_Dynamic_DP;

import java.util.Scanner;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/11726
 */
public class A11726_2xn_타일링 {
    static int N;

    public static void main(String[] args) {
        // write your code here
        A11726_2xn_타일링 main = new A11726_2xn_타일링();
        main.solution();
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] dp = new int[N + 2];
        dp[1] = 1;
        dp[2] = 2;

        /*
         10007 로 나눈 나머지를 구하므로 10006 이 최대값
         */
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp [ i - 2]) % 10007;
        }

        System.out.println(dp[N] % 10007);
    }
}
