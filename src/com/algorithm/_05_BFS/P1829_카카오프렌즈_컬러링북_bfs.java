package com.algorithm._05_BFS;

import java.util.*;

/**
 * @Date 2022/03/14
 * @URL https://programmers.co.kr/learn/courses/30/lessons/1829
 */
public class P1829_카카오프렌즈_컬러링북_bfs {
    static int graph[][];
    static boolean[][] visited;
    static int N;
    static int M;
    static int sumCount = 0;
    static List<Integer> sumList = new ArrayList<>();

    public static void main(String[] args) {
	    // write your code here
        P1829_카카오프렌즈_컬러링북_bfs main = new P1829_카카오프렌즈_컬러링북_bfs();

        int[][] a = new int[][] { {1,1,1,0}, {1,2,2,0}, {1,0,0,1}, {0,0,0,1}, {0,0,0,3}, {0,0,0,3}};
        System.out.println(Arrays.toString(main.solution(6, 4, a)));
    }

    public int[] solution(int m, int n, int[][] picture) {
        graph = picture;
        M = m;
        N = n;
        visited = new boolean[M][N];

        int thisCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) { // 방문하지 않았을때
                    if (graph[i][j] != 0) { // 0이 아닐때
                        sumCount = 1; // dfs 호출하므로 1부터 시작

                        bfs(i, j);
                        thisCount++; // 호출 개수 == 구역의 개수

                        sumList.add(sumCount);
                    }
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = thisCount;
        answer[1] = sumList.stream().max(Integer::compareTo).get();

        return answer;
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
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    /* 방문하지 않았고, 숫자가 같을 경우  */
                    if (!visited[nx][ny] && graph[nx][ny] == graph[x][y]) {
                        visited[nx][ny] = true;

                        sumCount++;

                        /* queue push */
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }
    }
}

/**
 * x, y 좌표를 위한 노드
 */
class Node {
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