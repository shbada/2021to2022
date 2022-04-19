package com.algorithm._04_DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 2022/04/19
 * @URL https://programmers.co.kr/learn/courses/30/lessons/42885
 */
public class A15650_N과M {
    static int N;
    static int M;

    static int[] arr;
    static boolean[] visited;
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        A15650_N과M main = new A15650_N과M();
        main.solution();

        dfs(0);

        /* 출력 */
        int index = 0;
        for (Integer integer : resultList) {
            if (index == M) {
                System.out.println();
                index = 0;
            }

            System.out.print(integer + " ");

            index++;
        }
    }

    public static void dfs(int index) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                list.add(arr[i]);
            }
        }

        if (list.size() == M) {
            resultList.addAll(list);
        }

        // 반복
        for (int i = index; i < arr.length; i++) {
            visited[i] = true;

            dfs(i + 1);

            visited[i] = false;
        }
    }

    public void solution() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        visited = new boolean[N];

        for (int i = 1; i < N + 1; i++) {
            arr[i - 1] = i;
        }
    }
}
