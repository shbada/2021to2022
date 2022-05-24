package com.algorithm._05_BFS.Dont;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date 2022/05/24
 * @URL https://programmers.co.kr/learn/courses/30/lessons/43162
 */
public class P43162_네트워크_oneRow {
    static int sumCount = 0;
    static boolean[] visited;

    public static void main(String[] args) {
        // write your code here
        P43162_네트워크_oneRow main = new P43162_네트워크_oneRow();

        int[][] a = new int[][] {{1,1,0}, {1,1,0}, {0,0,1}};
        System.out.println(main.solution(3, a));
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 방문하지 않았을때
                bfs(i, n, computers);
                sumCount++;
            }
        }

        answer = sumCount;
        return answer;
    }

    private static void bfs(int x, int n, int[][] graph) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(x);
        visited[x] = true;

        while (!q.isEmpty()) {
            /* 탐색 대상 : node */
            int target = q.poll();

            for (int j = 0; j < n; j++) {
                /* 방문 가능 조건 */
                if (!visited[j] && graph[target][j] == 1) {
                    visited[j] = true;

                    /* queue push */
                    q.offer(j);
                }
            }
        }
    }
}
