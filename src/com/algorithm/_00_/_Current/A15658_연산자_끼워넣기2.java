package com.algorithm._00_._Current;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Date 2022/04/28
 * @URL https://www.acmicpc.net/problem/15658
 */
public class A15658_연산자_끼워넣기2 {
    public static int[] arr;
    static int[] opArr;
    static int N; /* 열 개수 */
    public static ArrayList<Integer> resultList = new ArrayList<Integer>();

    public static void main(String[] args) {
        // write your code here
        A15658_연산자_끼워넣기2 main = new A15658_연산자_끼워넣기2();
        main.solution();
    }

    public void solution() {
        input();

        dfs(1, arr[0]); // arr[0]를 시작으로 dfs 함수 호출

        int max = resultList.stream()
                .mapToInt(x -> x)
                .max().getAsInt();

        int min = resultList.stream()
                .mapToInt(x -> x)
                .min().getAsInt();

        System.out.println(max); /* 최대값 */
        System.out.println(min); /* 최소값 */
    }

    static void dfs(int value, int sum) {
        for (int i = 0; i < 4; i++) {
            if (opArr[i] != 0) {
                opArr[i] = opArr[i] - 1; /* 연산자 사용 */

                if (i == 0) {
                    dfs(value + 1, sum + arr[value]);
                } else if (i == 1) {
                    dfs(value + 1, sum - arr[value]);
                } else if (i == 2) {
                    dfs(value + 1, sum * arr[value]);
                } else if (i == 3) {
                    dfs(value + 1, sum / arr[value]);
                }

                opArr[i] = opArr[i] + 1; /* 다시 롤백 */
            }
        }

        if (value == N) { /* 식이 완성되었음 */
            resultList.add(sum);
        }
    }
    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 열 개수
        arr = new int[N];
        opArr = new int[4];

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i = 0; i < 4; i++) {
            opArr[i] = sc.nextInt();
        }
    }
}
