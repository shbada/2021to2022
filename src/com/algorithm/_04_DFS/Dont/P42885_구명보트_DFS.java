package com.algorithm._04_DFS.Dont;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @Date 2022/03/25
 * @URL https://programmers.co.kr/learn/courses/30/lessons/42885
 */
public class P42885_구명보트_DFS {
    static int LIMIT;
    static int N;
    static int CNT;
    static boolean[] visited;
    static boolean[] used;
    static List<Integer> paramList = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        P42885_구명보트_DFS main = new P42885_구명보트_DFS();
        System.out.println(main.solution(new int[]{70, 50, 80, 50}, 100));
    }

    /**
     * DFS 구현 실패
     * @param people
     * @param limit
     * @return
     */
    public int solution(int[] people, int limit) {
        int answer = 0;
        N = people.length;

        LIMIT = limit;
        visited = new boolean[people.length];
        used = new boolean[people.length];

        for (int i = N; i > 0; i--) {
            combination(people, visited, 0, i);
        }

        return answer;
    }

    static void combination(int[] arr, boolean[] visited, int start, int r) {
        if (r == 0) {
            System.out.println(Arrays.toString(visited));

            int sum = paramList.stream()
                    .mapToInt(i -> i)
                    .sum();

            if (sum <= LIMIT) {
                IntStream.range(0, visited.length)
                        .filter(i -> visited[i])
                        .forEach(i -> used[i] = visited[i]);

                System.out.println("used : " + Arrays.toString(used));

                CNT++;
            }

            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            paramList.add(arr[i]);

            combination(arr, visited, i + 1, r - 1);

            visited[i] = false;
            paramList.remove(paramList.size() - 1);
        }
    }
}
