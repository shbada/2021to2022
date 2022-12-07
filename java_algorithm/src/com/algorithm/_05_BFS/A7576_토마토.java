package com.algorithm._05_BFS;

import java.util.*;

/**
 * @Date 2022/03/31
 * @URL https://www.acmicpc.net/problem/7576
 */
public class A7576_토마토 {
    static int[][] graph;
    static int N;
    static int M;
    static List<Node> tomatoList = new ArrayList<>();
    static boolean isAllExistTomato = true;
    static Queue<Node> queue = new LinkedList<>();

    /**
     * x, y 좌표를 위한 노드
     */
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
        A7576_토마토 main = new A7576_토마토();
        main.solution();
    }

    public void solution() {
        input();

        // 이미 토마토가 모두 익은 상태
        if (isAllExistTomato) {
            System.out.println(0);
            return;
        }

        // tomato 위치 push
        queue.addAll(tomatoList);

        bfs();

        int cnt = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 익지 않은 토마토 존재
                if (graph[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                cnt = Math.max(cnt, graph[i][j]);
            }
        }

        // 총 걸린 일수는 -1
        System.out.println(cnt - 1);
    }

    private static void bfs() {
        /* 이동 상, 하, 좌, 우 */
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        while (!queue.isEmpty()) {
            /* 탐색 대상 : node */
            Node node = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.getX() + dx[i];
                int ny = node.getY() + dy[i];

                /* 방문 가능 조건 */
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    /* 방문하지 않았고, 숫자가 같을 경우  */
                    if (graph[nx][ny] == 0) {
                        // 익은 토마토 걸린 일수 셋팅
                        graph[nx][ny] = graph[node.getX()][node.getY()] + 1;

                        /* queue push */
                        queue.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();

        graph = new int[N][M];

        // graph 셋팅
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = sc.nextInt();
                graph[i][j] = n;

                // 토마토 위치 설정
                if (n == 1) {
                    tomatoList.add(new Node(i, j));
                } else if (n == 0) {
                    // 이 로직을 타지 않는다면, 모든 토마토가 이미 모두 익은 상태
                    isAllExistTomato = false;
                }

            }
        }
    }
}
