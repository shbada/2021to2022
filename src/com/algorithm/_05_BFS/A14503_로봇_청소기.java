package com.algorithm._05_BFS;

import java.util.Scanner;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/14503
 */
public class A14503_로봇_청소기 {
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

    public static int cnt;

    public static int currentDirection;

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
    }

    public static void main(String[] args) {
        // write your code here
        A14503_로봇_청소기 main = new A14503_로봇_청소기();
        main.solution();
    }

    public void solution() {
        input();

        simulation(X, Y, DIRECTION);
    }

    private void simulation(int x, int y, int direction) {
        cnt++; // 현재 영역 청소
        visited[x][y] = true; // 청소 완료

        currentDirection = direction;

        /* 이동 0(북), 1(동), 2(남), 3(서) */
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};

        int backCnt = 0;

        while (true) {
            // 회전
            currentDirection = leftTurn(currentDirection);

            int nx = x + dx[currentDirection];
            int ny = y + dy[currentDirection];

            /* 청소 하지 않은 영역이고, 벽이 아니라면 */
            if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (!visited[nx][ny] && arr[nx][ny] != 1) {
                    x = nx;
                    y = ny;

                    cnt++; // 현재 영역 청소
                    visited[nx][ny] = true; // 청소 완료

                    backCnt = 0; // 리셋 (연속으로 네 번 실행이 아니므로)
                } else {
                    backCnt++;
                }
            } else {
                backCnt++;
            }

            // 2-b. 바로 뒤쪽을 확인 후, 벽이라면 멈추고 그렇지 않다면 한칸 후진한다.
            if (backCnt == 4) {
                int backDirection = back(currentDirection);

                x = x + dx[backDirection];
                y = y + dy[backDirection];

                // 벽이라면 작동을 멈춘다.
                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (arr[x][y] == 1) {
                        break;
                    }
                }

                backCnt = 0; // 리셋
            }
        }

        System.out.println(cnt);

    }

    private int back(int direction) {
        return (direction + 2) % 4;
    }

    private int leftTurn(int direction) {
        return (direction + 3) % 4;
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
