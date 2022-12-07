package com.algorithm._04_DFS;

/*
N, M 크기의 얼음 틀이 있다.
구멍이 뚫려 있는 부분은 0, 칸막이가 존재하는 부분은 1로 표시된다.
구멍이 뚫려 있는 부분끼리 상, 하, 좌, 우로 붙어 있는 경우 서로 연결되어 있는 것으로 간주한다.
이때 얼음 틀의 모양이 주어졌을 때 생성되는 총 아이스크림의 개수를 구하는 프로그램을 작성하시오.
 */

import java.util.Scanner;

/**
 * @Date 2022/03/20
 * @URL 이것이코딩테스트다 chapter05
 */
public class Tchapter05_음료수_얼려먹기 {
    static int[][] graph;
    static int N;
    static int M;
    static boolean[][] visited; // 방문처리를 위한 변수

    public static void main(String[] args) {
	    // write your code here
        Tchapter05_음료수_얼려먹기 main = new Tchapter05_음료수_얼려먹기();
        main.solution();
    }

    public void solution() {
        input();

        /* 아이스크림 개수를 저장할 변수 */
        int count = 0;

        for (int i = 1; i < N + 1; i++) { // 행
            for (int j = 1; j < M + 1; j++) { // 열
                /* 구멍이 뚫려있을 경우 + 방문하지 않았을 경우 */
                /* 구멍이 뚫려있을 경우 호출해야한다. (개수를 세려면 0 을 시작점으로) */
                if (graph[i][j] == 0 && !visited[i][j]) {
                    /* 탈출조건 존재 */
                    dfs(i, j);

                    /* 위 dfs 에서 탈출되면 아이스크림 1개가 존재하다는 것을 의미 */
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    /**
     * dfs
     * @param i
     * @param j
     */
    private static void dfs(int i, int j) {
        /* 방문 처리 */
        visited[i][j] = true;

        /* 이동 상, 하, 좌, 우 */
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        for (int index = 0; index < dx.length; index++) {
            int nx = i + dx[index];
            int ny = j + dy[index];

            /* 접근 가능한 조건 */
            if (nx > 0 && nx < N + 1 && ny > 0 && ny < M + 1) {
                if (graph[nx][ny] == 0) { /* 구멍이 뚫려있을 경우 */
                    if (!visited[nx][ny]) { /* 방문 체크 */
                        dfs(nx, ny);
                    }
                }
            }
        }
    }

    static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 행
        M = sc.nextInt(); // 열

        // index 1 부터 시작
        graph = new int[N + 1][M + 1];
        visited = new boolean[N + 1][M + 1];

        /* 인접행렬 생성 */
        for (int i = 0; i < N; i++) {
            String s = sc.next();

            for (int j = 0; j < M; j++) {
                graph[i + 1][j + 1] = s.charAt(j) - '0';
            }
        }
    }
}
