package com.algorithm._05_BFS.Dont;

import java.util.*;

/**
 * @Date 2022/03/30
 * @URL https://www.acmicpc.net/problem/2146
 */
public class A2146_다리_만들기 {
    static int[][] graph;
    static int N;
    static boolean[][] visited;
    static Set<Node> nodeSet = new HashSet<>();
    static int thisCount = 0;
    /* 이동 상, 하, 좌, 우 */
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    /**
     * x, y 좌표를 위한 노드
     */
    static class Node {
        private int x;
        private int y;
        private int bridge;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int bridge) {
            this.x = x;
            this.y = y;
            this.bridge = bridge;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public int getBridge() {
            return bridge;
        }
    }
    
    public static void main(String[] args) {
	    // write your code here
        A2146_다리_만들기 main = new A2146_다리_만들기();
        main.solution();
    }

    public void solution() {
        input();

        /** 섬 번호 설정하기 */
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문하지 않았을때
                    if (graph[i][j] != 0) { // 0이 아닐때
                        thisCount++; // 호출 개수 == 구역의 개수
                        bfs(i, j);
                    }
                }
            }
        }


        /** 섬과 섬 사이의 최단 거리 구하기 */
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] != 0) { // 섬일 경우
                    visited = new boolean[N][N];

                    // res : 섬과 섬의 거리
                    int res = bridge(i, j);

                    if (res == -1) {
                        continue;
                    }

                    // 최소값 저장
                    min = Math.min(min, res);
                }
            }
        }

        System.out.println(min - 1);
    }
    
    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;
        graph[x][y] = thisCount;

        while (!q.isEmpty()) {
            /* 탐색 대상 : node */
            Node node = q.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = node.getX() + dx[i];
                int ny = node.getY() + dy[i];

                /* 방문 가능 조건 */
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    /* 방문하지 않았고, 숫자가 같을 경우  */
                    if (!visited[nx][ny] && graph[nx][ny] != 0) {
                        visited[nx][ny] = true;

                        /* queue push */
                        q.offer(new Node(nx, ny));

                        // 다리 번호로 셋팅
                        graph[nx][ny] = thisCount;
                    }
                }
            }
        }
    }

    private static int bridge(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        visited[x][y] = true;

        // 섬 번호 추출 (1, 2, 3...)
        int num = graph[x][y];

        while (!q.isEmpty()) {
            Node pos = q.poll();
            int px = pos.x;
            int py = pos.y;
            int bridge = pos.bridge; // 섬과 섬까지의 거리 저장

            // 섬 발견 + 같은 번호의 섬이 아닐 경우
            if(graph[px][py] != 0 && graph[px][py] != num) {
                /*
                   BFS 탐색은 인접 노드부터 탐색하기 떄문에,
                   제일 먼저 찾은 값이 가장 최단 거리이기 때문에 값을 받으면 무조건 return 한다.
                 */
                return bridge; // 거리 결과 리턴
            }

            // 상, 하, 좌, 우 이동
            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                /* 방문 가능 조건 */
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    // 방문 했을 경우 + 같은 섬일 경우 PASS
                    if (visited[nx][ny] || graph[nx][ny] == num) {
                        continue;
                    }

                    visited[nx][ny] = true;

                    // 거리 + 1 씩 증가
                    q.add(new Node(nx, ny, bridge + 1));
                }
            }
        }

        return -1;
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        graph = new int[N][N];
        visited = new boolean[N][N];

        // graph 셋팅
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
    }
}
