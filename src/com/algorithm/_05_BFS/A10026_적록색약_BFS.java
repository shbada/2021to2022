package com.algorithm._05_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Date 2022/03/17
 * @URL https://www.acmicpc.net/problem/10026
 */
public class A10026_적록색약_BFS {
    static char[][] graph;
    static int N;
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
        A10026_적록색약_BFS main = new A10026_적록색약_BFS();
        main.solution();
    }

    public void solution() {
        input();

        int thisCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문하지 않았을때
                    if (graph[i][j] != 0) { // 0이 아닐때

                        bfs(i, j);
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

                        bfs(i, j);
                        thisCount2++; // 호출 개수 == 구역의 개수
                    }
                }
            }
        }

        System.out.println(String.format("%s %s", thisCount, thisCount2));
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;

        /* 이동 상, 하, 좌, 우 */
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};

        while (!q.isEmpty()) {
            /* 탐색 대상 : node */
            Node node = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.getX() + dx[i];
                int ny = node.getY() + dy[i];

                /* 방문 가능 조건 */
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    /* 방문하지 않았고, 숫자가 같을 경우  */
                    if (!visited[nx][ny] && graph[nx][ny] == graph[x][y]) {
                        visited[nx][ny] = true;

                        /* queue push */
                        q.offer(new Node(nx, ny));
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
