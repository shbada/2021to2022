package com.algorithm._05_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/14503
 */
public class A14503_로봇_청소기_BFS1 {
    // 변수 선언
    public static int N;
    public static int M;
    public static int X;
    public static int Y;
    public static int DIRECTION;

    // 방문한 위치를 저장하기 위한 맵
    public static boolean[][] visited = new boolean[50][50];

    // 전체 맵 정보
    public static int[][] arr = new int [50][50];

    /**
     * x, y 좌표를 위한 노드
     */
    static class Node {
        private int x;
        private int y;
        private int direction;

        public Node(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getDirection() {
            return direction;
        }
    }

    public static void main(String[] args) {
        // write your code here
        A14503_로봇_청소기_BFS main = new A14503_로봇_청소기_BFS();
        main.solution();
    }

    public void solution() {
        input();

        bfs(X, Y, DIRECTION);
    }

    private static void bfs(int x, int y, int direction) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, direction));
        visited[x][y] = true;

        DIRECTION = direction;

        /* 이동 0(북), 1(동), 2(남), 3(서) */
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        int cnt = 1;
        while (!q.isEmpty()) {
            /* 탐색 대상 : node */
            Node node = q.poll();
            boolean flag = false;

            // 4방향 검사
            for (int i = 0; i < 4; i++) {
                DIRECTION = turnLeft(DIRECTION);

                int nx = node.x + dx[DIRECTION];
                int ny = node.y + dy[DIRECTION];

                // 아직 청소하지 않은 빈 공간이라면
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visited[nx][ny] && arr[nx][ny] != 1) {
                        // 전진
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny, DIRECTION));

                        cnt++;

                        flag = true;
                        break;
                    }
                }
            }

            if (!flag) {
                // 뒤쪽
                int curDir = turnBack(DIRECTION);

                int nx = node.x + dx[curDir];
                int ny = node.y + dx[curDir];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (arr[nx][ny] != 1) { // 벽이 아닐 경우
                        cnt++;
                        q.add(new Node(nx, ny, DIRECTION));
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(cnt);
    }


    public static int turnLeft(int direction) {
        return (direction + 3) % 4;
    }

    public static int turnBack(int direction) {
        return (direction + 2) % 4;
    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // N (행)
        M = sc.nextInt(); // M (열)

        X = sc.nextInt();
        Y = sc.nextInt();
        DIRECTION = sc.nextInt();

        /* 행렬 입력 */
        for (int i = 0; i < N; i++) { // n (행)
            for (int j = 0; j < M; j++) { // m (열)
                arr[i][j] = sc.nextInt();
            }
        }
    }
}
