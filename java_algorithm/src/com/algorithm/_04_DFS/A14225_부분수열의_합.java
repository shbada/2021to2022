package com.algorithm._04_DFS;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @Date 2022/03/16
 * @URL https://www.acmicpc.net/problem/14225
 */
public class A14225_부분수열의_합 {
    static int N;
    static int[] arr;
    static Set<Integer> sumSet = new HashSet<>(); // 부분수열의 합을 담을 Set (중복X)
    static boolean[] visited; //스킵 판별

    public static void main(String[] args) {
	    // write your code here
        A14225_부분수열의_합 main = new A14225_부분수열의_합();
        main.solution();
    }

    /*
     5 1 2

     1 + 2 = 3
     1 + 5 = 6
     2 + 5 = 7
     1 + 2 + 5 = 8
     */
    public void solution() {
        input();

        if (arr.length == 1) {
            if (arr[0] > 1) {
                System.out.println(1);
                return;
            } else {
                System.out.println(arr[0] + 1);
                return;
            }
        }

        dfs(0);

        List<Integer> sumList = new ArrayList<>(sumSet);
        Collections.sort(sumList); /* 정렬 */

        int result = sumList.get(sumList.size() - 1) + 1;

        result = IntStream.range(1, sumList.size())
                .filter(i -> i != sumList.get(i - 1))
                .findFirst().orElse(result);

        System.out.println(result);
    }

    private void dfs(int index) {
        // 계산
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i]) { /* true 일 경우 */
                sum += arr[i];

                /* Set으로 설정하여 중복값 제거 */
                sumSet.add(sum);
            }
        }

        // 수행
        for (int i = index; i < arr.length; i++) {
            visited[i] = true;

            dfs(i + 1);

            visited[i] = false;
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        N = Integer.parseInt(sc.nextLine());

        arr = new int[N];
        visited = new boolean[N];

        /* 집합 S */
        String S = sc.nextLine();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(S.split(" ")[i]);
        }
    }
}
