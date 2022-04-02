package com.algorithm._00_._Current;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Date 2022/04/02
 * @URL https://www.acmicpc.net/problem/1525
 */
public class A1525_퍼즐 {
    static int[][] graph;
    static int N;
    static int sumCount = 0;
    static boolean[][] visited;
    static Node emptyNode;

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
        A1525_퍼즐 main = new A1525_퍼즐();
        main.solution();
    }

    public void solution() {
        input();

        bfs();
        System.out.println(sumCount);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(emptyNode);
        visited[emptyNode.x][emptyNode.y] = true;

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
                    if (!visited[nx][ny]) {
                        // 위치 기반으로 좌측 -1, 우측 +1 조건 설정
                        if (graph[nx][ny - 1] == graph[nx][ny] - 1
                                && graph[nx][ny + 1] == graph[nx][ny] + 1) {
                            visited[nx][ny] = true;

                            swap(nx, ny, node.getX(), node.getY());

                            /* queue push */
                            q.offer(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }

    private static void swap(int nx, int ny, int x, int y) {
        int n = graph[x][y];
        int newN = graph[nx][ny];

        graph[x][y] = newN;
        graph[nx][ny] = n;
    }

    private void input() {
        N = 3;

        graph = new int[N][N];
        visited = new boolean[N][N];

        Scanner sc = new Scanner(System.in);
        // graph 셋팅
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = sc.nextInt();
                if (value == 0) {
                    emptyNode = new Node(i, j);
                    graph[i][j] = value;
                } else {
                    graph[i][j] = 1;
                }
            }
        }
    }
}
