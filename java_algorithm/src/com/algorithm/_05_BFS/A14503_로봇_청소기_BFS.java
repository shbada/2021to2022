package com.algorithm._05_BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/14503
 */
public class A14503_로봇_청소기_BFS {
    // 변수 선언
    public static int N;
    public static int M;
    public static int X;
    public static int Y;
    public static int DIRECTION;

    public static int answer = 0;

    // 방문한 위치를 저장하기 위한 맵
    public static boolean[][] visited;

    // 전체 맵 정보
    public static int[][] inputAry;

    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1};

    /**
     * x, y 좌표를 위한 노드
     */
    static class Robot {
        private int x;
        private int y;
        private int direction;

        public Robot(int x, int y, int direction) {
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

    private static void bfs(int paramX, int paramY, int direction) {
        Queue<Robot> q = new LinkedList<Robot>();
        q.add(new Robot(paramX, paramY, direction));
        answer++;
        visited[paramX][paramY] = true;

        while(!q.isEmpty()) {
            Robot r = q.remove();
            int x = r.x;
            int y = r.y;
            int d = r.direction;

            int next_direction = d;
            boolean flag = false;

            // 2번 과정 : 4방향 검사
            for(int i = 0; i < 4; i++) {
                // 1번 과정
                next_direction = turnLeft(next_direction);

                int nx = x + dx[next_direction];
                int ny = y + dy[next_direction];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(inputAry[nx][ny] == 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new Robot(nx,ny,next_direction));

                        ++answer;
                        flag = true;

                        break;
                    }
                }
            }

            // 3번 과정
            if(!flag) {
                next_direction = turnBack(d);

                int nx = x + dx[next_direction];
                int ny = y + dy[next_direction];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if(inputAry[nx][ny] == 0) {
                        q.add(new Robot(nx,ny,d));
                    }
                }
            }
        }

        System.out.println(answer);
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

        inputAry = new int[N][M];
        visited = new boolean[N][M];

        /* 행렬 입력 */
        for (int i = 0; i < N; i++) { // n (행)
            for (int j = 0; j < M; j++) { // m (열)
                inputAry[i][j] = sc.nextInt();
            }
        }
    }
}
