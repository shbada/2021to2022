package com.algorithm._05_BFS.Dont;

import java.util.*;

public class P81302_거리두기_확인하기 {
    static boolean[][] visit;
    static boolean flag;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

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
        P81302_거리두기_확인하기 main = new P81302_거리두기_확인하기();
        System.out.println(Arrays.toString(main.solution(new String[][]{
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" },
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" },
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" }
        })));
    }

    public Integer[] solution(String[][] places) {
        Integer[] answer = new Integer[5];

        for (int i = 0; i < 5; i++) {
            flag = false;
            visit = new boolean[5][5];

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < places[i][j].length(); k++) {
                    if (places[i][j].charAt(k) == 'P' && !visit[j][k])
                        bfs(j, k, places[i]);
                }
            }

            if (!flag)
                answer[i] = 1;
            else
                answer[i] = 0;

        }

        return answer;
    }

    public static void bfs(int x, int y, String[] p) {
        Queue<Node> qu = new LinkedList<>();
        qu.add(new Node(x, y));
        visit[x][y] = true;

        while (!qu.isEmpty()) {
            Node node = qu.poll();
            int getX = node.getX();
            int getY = node.getY();

            for (int i = 0; i < 4; i++) {
                int nx = getX + dx[i];
                int ny = getY + dy[i];

                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !visit[nx][ny]) {
                    int distance = Math.abs(nx - x) + Math.abs(ny - y);
                    if (p[nx].charAt(ny) == 'P' && distance <= 2) {
                        flag = true;
                        return;
                    }
                    else if (p[nx].charAt(ny) == 'O' && distance == 1) {
                        qu.add(new Node(nx, ny));
                        visit[nx][ny] = true;
                    }
                }
            }
        }
    }
}
