package com.algorithm._04_DFS;

import java.util.*;

/**
 * @Date 2022/03/18
 * @URL https://programmers.co.kr/learn/courses/30/lessons/12936
 */
public class P12936_줄서는_방법 {
    static boolean[] visited; //스킵 판별
    static int N;
    static int[] arr;
    static int[] makeArr;
    static List<int[]> resultList = new ArrayList<>();

    public static void main(String[] args) {
	    // write your code here
        P12936_줄서는_방법 main = new P12936_줄서는_방법();
        System.out.println(Arrays.toString(main.solution(3, 5)));
    }

    /*
     n = 3
     1, 2, 3
     1, 3, 2
     2, 1, 3
     2, 3, 1
     3, 1, 2
     3, 2, 1

     5번째 - 3,1,2
     */
    public int[] solution(int n, long k) {
        N = n;
        arr = new int[n];
        makeArr = new int[n];
        visited = new boolean[n];

        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }

        dfs(0);

        return resultList.get(Long.valueOf(k).intValue() - 1);
    }

    private void dfs(int target) {
        // 계산
        if (target == N) {
            System.out.println("answer: " + Arrays.toString(makeArr));

            int[] dupArr = new int[N];
            for (int i = 0; i < N; i++) {
                dupArr[i] = makeArr[i];
            }

            resultList.add(dupArr);
        }

        // 수행
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;

                makeArr[target] = arr[i];

                dfs(target + 1);

                visited[i] = false;
            }
        }
    }
}
