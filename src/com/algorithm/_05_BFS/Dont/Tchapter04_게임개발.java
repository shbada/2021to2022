package com.algorithm._05_BFS.Dont;

/*
현민이는 게임 캐릭터가 맵 안에서 움직이는 시스템을 개발 중이다.
캐릭터가 있는 장소는 1 X 1 크기의 정사각형으로 이뤄진 N X M 크기의 직사각형으로, 각각의 칸은 육지 또는 바다이다.
 캐릭터는 동서남북 중 한 곳을 바라본다.

맵의 각 칸은 (A, B)로 나타낼 수 있고, A는 북쪽으로부터 떨어진 칸의 개수, B는 서쪽으로부터 떨어진 칸의 개수이다.
캐릭터는 상하좌우로 움직일 수 있고, 바다로 되어 있는 공간에는 갈 수 없다.
캐릭터의 움직임을 설정하기 위해 정해 놓은 매뉴얼은 이러하다.

1. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향(반시계 방향으로 90도 회전한 방향)부터 차례대로 갈 곳을 정한다.

2. 캐릭터의 바로 왼쪽 방향에 아직 가보지 않은 칸이 존재한다면, 왼쪽 방향으로 회전한회다음 왼쪽으로 한 칸을 전진한다.
왼쪽 방향에 가보지 않은 칸이 없다면, 왼쪽 방향으로 회전만 수행하고 1단계로 돌아간다.

3. 만약 네 방향 모두 이미 가본 칸이거나 바다로 되어 있는 칸인 경우에는, 바라보는 방향을 유지한 채로 한 칸 뒤로 가고 1단계로 돌아간다.
단, 이때 뒤쪽 방향이 바다인 칸이라 뒤로 갈 수 없는 경우에는 움직임을 멈춘다.

현민이는 위 과정을 반복적으로 수행하면서 캐릭터의 움직임에 이상이 있는지 테스트하려고 한다.
메뉴얼에 따라 캐릭터를 이동시킨 뒤에, 캐릭터가 방문한 칸의 수를 출력하는 프로그램을 만드시오.

<입력 조건>

첫째 줄에 맵의 세로 크기 N과 가로 크기 M을 공백으로 구분하여 입력한다. (3 <= N, M <= 50)

둘째 줄에 게임 캐릭터가 있는 칸의 좌표 (A, B)와 바라보는 방햔 d가 각각 서로 공백으로 구분하여 주어진다.
방향 d의 값으로는 다음과 같이 4가지가 존재한다.

0 : 북쪽
1 : 동쪽
2 : 남쪽
3 : 서쪽
셋째 줄부터 맵이 육지인지 바다인지에 대한 정보가 주어진다.
N개의 줄에 맵의 상태가 북쪽부터 남쪽 순서대로, 각 줄의 데이터는 서쪽부터 동쪽 순서대로 주어진다.
맵의 외각은 항상 바다로 되어 있다.

0 : 육지
1 : 바다
처음에 게임 캐릭터가 위치한 칸의 상태는 항상 육지이다.

<출력 조건>
첫째 줄에 이동을 마친 후 캐릭터가 방문한 칸의 수를 출력한다.

<입력 예시>                                       <출력 예시>
4 4                                             3
1 1 0 // (1, 1)에 북쪽(0)을 바라보고 서 있는 캐릭터
1 1 1 1
1 0 0 1
1 1 0 1
1 1 1 1

 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @Date 2022/03/20
 * @URL 이것이코딩테스트다 chapter04
 */
public class Tchapter04_게임개발 {
    // 변수 선언
    public static int N, M, X, Y, DIRECTION;

    // 방문한 위치를 저장하기 위한 맵
    public static boolean[][] visited = new boolean[50][50];

    // 전체 맵 정보
    public static int[][] arr = new int [50][50];

    // 북, 동, 남, 서 방향 정의
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

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
        Tchapter04_게임개발 main = new Tchapter04_게임개발();
        main.solution();
    }

    public void solution() {
        input();

        bfs(X, Y, DIRECTION);
    }

    private static void bfs(int x, int y, int direction) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, direction));
        // visited[x][y] = true;

        /* 이동 0(북), 1(동), 2(남), 3(서) */
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{-1, 0, 1, 0};

        int cnt = 0;

        while (!q.isEmpty()) {
            /* 탐색 대상 : node */
            Node node = q.poll();

            int isVisit = 1;
            for (int i = 0; i < dx.length; i++) {
                // 왼쪽 방향을 바라보도록 위치 셋팅
                // 0(북), 1(동), 2(남), 3(서)
                // 0(북) -> 3(서)
                // 1(동) -> 0(북)
                // 2(남) -> 1(동)
                // 3(서) -> 2(남)
                int idx = (node.direction + i) % 4;

                int nx = node.x + dx[idx];
                int ny = node.y + dy[idx];

                if (nx < N && ny < M & nx >= 0 && ny >= 0) {
                    // 방문하지 않았고, 바다가 아닐 경우 이동 가능
                    if(!visited[nx][ny] && arr[nx][ny] != 1) {
                        visited[nx][ny] = true;
                        cnt++;
                        q.add(new Node(nx, ny, idx));
                    } else { // 이동 불가능
                        isVisit++; // 4회 체크 필요하므로 set
                    }
                }
            }

            // 4 방향 모두 이동 불가 시
            if (isVisit == 4) {
                // "바라보는 방향을 유지한 채로 한칸 뒤로 가고"
                // 현재 위치 값 + 2 : 캐릭터의 뒤 칸의 위치
                // -> 0(북), 1(동), 2(남), 3(서) 일때 캐릭터가 바라보는 위치가 0(북) + 2 => 2(남)이 되기 때문에
                int idx = (node.direction + 2) % 4;

                int nx = node.x + dx[idx];
                int ny = node.y + dy[idx];

                if (arr[nx][ny] != 1) { // 육지의 경우 이동
                    q.add(new Node(nx, ny, node.direction));
                } else { // 바다인 경우 움직임을 멈춘다.
                    break;
                }
            }
        }

        System.out.println(cnt);
    }

    /*
        4 4
        1 1 0
        1 1 1 1
        1 0 0 1
        1 1 0 1
        1 1 1 1
     */
    private void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // N (행)
        M = sc.nextInt(); // M (열)

        X = sc.nextInt();
        Y = sc.nextInt();
        DIRECTION = sc.nextInt();

        /* 현재 좌표 방문 처리 */
        // visited[x][y] = true;

        /* 행렬 입력 */
        for (int i = 0; i < N; i++) { // n (행)
            for (int j = 0; j < M; j++) { // m (열)
                arr[i][j] = sc.nextInt();
            }
        }
    }
}
