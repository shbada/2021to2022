package com.algorithm._04_DFS.Dont;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 2022/03/17
 * @URL https://www.acmicpc.net/problem/16197
 */
public class A16197_두_동전 {
    static String[][] graph;
    static int N;
    static int M;
    static boolean[][] visited;
    static List<Node> coins = new ArrayList<>();
    static int min = Integer.MAX_VALUE;

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }
    }

    public static void main(String[] args) {
	    // write your code here
        A16197_두_동전 main = new A16197_두_동전();
        main.solution();
    }

    public void solution() {
        input();

        dfs(coins.get(0).x, coins.get(0).y, coins.get(1).x, coins.get(1).y, 0);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    static void dfs(int x1, int y1, int x2, int y2, int cnt) {
        /* 10회 이상이면 -1 */
        if(cnt > 10) {
            return;
        }

        /* 둘이 동시에 벗어나도 리턴 */
        if ((isNotMoved(x1, y1) && isNotMoved(x2, y2))) {
            return;
        }

        /** 조건 만족) 내부에 있고, 2개의 동전 중 1개는 아웃 */
        if ((!isNotMoved(x1, y1) && isNotMoved(x2, y2)) || (isNotMoved(x1, y1) && !isNotMoved(x2, y2))) {
            /* min 갱신 */
            if (min > cnt) {
                min = cnt;
            }

            return;
        }

        int[] dx = new int[]{0, -1, 0, 1};
        int[] dy = new int[]{1, 0, -1, 0};

        for (int i = 0; i < 4; i++) {
            // coin1
            int nx1 = x1 + dx[i];
            int ny1 = y1 + dy[i];

            // coin2
            int nx2 = x2 + dx[i];
            int ny2 = y2 + dy[i];

            // 이동 불가능한 경우 기존 위치로 셋팅
            if (!isNotMoved(nx1, ny1) && !isNotWall(nx1, ny1)) {
                // 이동 불가능하므로 원래의 위치로 셋팅
                nx1 = x1;
                ny1 = y1;
            }

            // 이동 불가능한 경우 기존 위치로 셋팅
            if (!isNotMoved(nx2, ny2) && !isNotWall(nx2, ny2)) {
                // 이동 불가능하므로 원래의 위치로 셋팅
                nx2 = x2;
                ny2 = y2;
            }

            dfs(nx1, ny1, nx2, ny2, cnt + 1);
        }
    }

    private static boolean isNotMoved(int x, int y) {
        return (x < 0 || y < 0 || x >= N || y >= M);
    }

    private static boolean isNotWall(int x, int y) {
        return !"#".equals(graph[x][y]);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        graph = new String[N][M];
        visited = new boolean[N][M];

        // graph 셋팅
        for (int i = 0; i < N; i++) {
            String s = sc.next();

            for (int j = 0; j < M; j++) {
                String target = s.split("")[j];
                graph[i][j] = target;

                // 동전의 위치 put
                if ("o".equals(target)) {
                    coins.add(new Node(i, j));
                }
            }
        }
    }
}
