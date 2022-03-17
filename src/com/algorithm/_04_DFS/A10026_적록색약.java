package com.algorithm._04_DFS;

import java.util.Scanner;

/**
 * @Date 2022/03/15
 * @URL https://www.acmicpc.net/problem/10026
 */
public class A10026_적록색약 {
    static char[][] graph;
    static int N;
    static boolean[][] visited;

    public static void main(String[] args) {
	    // write your code here
        A10026_적록색약 main = new A10026_적록색약();
        main.solution();
    }

    public void solution() {
        input();

        int thisCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문하지 않았을때
                    if (graph[i][j] != 0) { // 0이 아닐때

                        dfs(i, j);
                        thisCount++; // 호출 개수 == 구역의 개수
                    }
                }
            }
        }

        /* 초록을 빨강으로 변경해준다. G -> R */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 'G') {
                    graph[i][j] = 'R';
                }
            }
        }

        int thisCount2 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문하지 않았을때
                    if (graph[i][j] != 0) { // 0이 아닐때

                        dfs(i, j);
                        thisCount2++; // 호출 개수 == 구역의 개수
                    }
                }
            }
        }

        System.out.println(String.format("%s %s", thisCount, thisCount2));
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;

        int[] dx = new int[]{0, -1, 0, 1};
        int[] dy = new int[]{1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (!visited[nx][ny]) { // 방문하지 않았을때
                    if (graph[nx][ny] != 0 && graph[nx][ny] == graph[x][y]) { // 0이 아닐때, 숫자가 같을때
                        dfs(nx, ny);
                    }
                }
            }
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        graph = new char[N][N];
        visited = new boolean[N][N];

        // graph 셋팅
        for (int i = 0; i < N; i++) {
            String s = sc.next();

            // RRRBB...
            for (int j = 0; j < N; j++) {
                graph[i][j] = s.charAt(j);
            }
        }
    }
}
