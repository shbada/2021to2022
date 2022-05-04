package com.algorithm._05_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Date 2022/05/04
 * @URL https://www.acmicpc.net/problem/2636
 */
public class A2636_치즈 {
    static int[][] graph;
    static int N;
    static int M;

    static int cheeseCnt;

    static Queue<Node> queue = new LinkedList<>();

    static boolean[][] visited;

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
        A2636_치즈 main = new A2636_치즈();
        main.solution();
    }

    public void solution() {
        input();

        int callCnt = 0; // BFS 호출 횟수 (=시간)
        int remainder = 0; // BFS 호출 횟수 (=시간)

        // 치즈가 모두 녹을때까지 bfs 를 호출한다.
        while (cheeseCnt > 0) {
            callCnt++;

            visited = new boolean[N][M]; // 호출할때마다 리셋

            // 마지막 치즈를 녹이기전의 개수이므로, bfs 호출 전에 셋팅하면 마지막에 셋팅된 값이 결과다.
            remainder = cheeseCnt;
            bfs(0, 0);
        }

        System.out.println(callCnt); // 걸린시간
        System.out.println(remainder); // 남은 치즈 개수
    }

    private static void bfs(int x, int y) {
        queue.offer(new Node(x, y));
        visited[x][y] = true;

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
                    /* 방문하지 않은 경우  */
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;

                        /* 치즈일 경우 */
                        if (graph[nx][ny] == 1) {
                            // 녹는다.
                            cheeseCnt = cheeseCnt - 1;

                            // 치즈는 녹아서 사라진다.
                            graph[nx][ny] = 0;

                            // queue 에 담지 않는다.
                            // 담으면 가장자리가 아닌 부분을 탐색하게 되기 때문
                        } else if(graph[nx][ny] == 0) {
                            /* queue push */
                            queue.offer(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // N (행)
        M = sc.nextInt(); // M (열)

        graph = new int[N][M];

        /* 행렬 입력 */
        for (int i = 0; i < N; i++) { // n (행)
            for (int j = 0; j < M; j++) { // m (열)
                graph[i][j] = sc.nextInt();

                if (graph[i][j] == 1) {
                    cheeseCnt++;
                }
            }
        }
    }
}
