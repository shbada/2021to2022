package com.algorithm._04_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2022/03/14
 * @URL https://programmers.co.kr/learn/courses/30/lessons/1829
 */
public class P1829_카카오프렌즈_컬러링북 {
    static int graph[][];
    static boolean[][] visited;
    static int N;
    static int M;
    static int sumCount = 0;
    static List<Integer> sumList = new ArrayList<>();

    public static void main(String[] args) {
	    // write your code here
        P1829_카카오프렌즈_컬러링북 main = new P1829_카카오프렌즈_컬러링북();

        int[][] a = new int[][] { {1,1,1,0}, {1,2,2,0}, {1,0,0,1}, {0,0,0,1}, {0,0,0,3}, {0,0,0,3}};
        System.out.println(Arrays.toString(main.solution(6, 4, a)));
    }

    public int[] solution(int m, int n, int[][] picture) {
        int[] answer = new int[2];

        graph = picture;
        M = m;
        N = n;
        visited = new boolean[M][N];

        int thisCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문하지 않았을때
                    if (graph[i][j] != 0) { // 0이 아닐때
                        sumCount = 1; // dfs 호출하므로 1부터 시작

                        dfs(i, j);
                        thisCount++; // 호출 개수 == 구역의 개수

                        sumList.add(sumCount);
                    }
                }
            }
        }

        answer[0] = thisCount;
        answer[1] = sumList.stream().max(Integer::compareTo).get();

        return answer;
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        int[] dx = new int[]{0, -1, 0, 1};
        int[] dy = new int[]{1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                if (!visited[nx][ny]) { // 방문하지 않았을때
                    if (graph[nx][ny] != 0 && graph[nx][ny] == graph[x][y]) { // 0이 아닐때, 숫자가 같을때
                        sumCount++; // 해당 구역의 총 합
                        dfs(nx, ny);
                    }
                }
            }
        }
    }
}
