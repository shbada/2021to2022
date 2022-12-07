package com.algorithm._05_BFS.Dont;

import java.util.*;

/**
 * @Date 2022/04/02
 * @URL https://www.acmicpc.net/problem/1525
 */
public class A1525_퍼즐 {
    /**
     * 힌트1) 2차원 배열이 아닌 String 으로 만든다.
     */
    static StringBuilder start = new StringBuilder();
    static Queue<String> dq = new LinkedList<>();
    static Map<String,Integer> map = new HashMap<>();
    static Node emptyNode;

    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,-1,0,1};

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

        System.out.println(bfs());
    }

    private static int bfs() {
        dq.offer(start.toString());
        map.put(start.toString(), 0);

        while(!dq.isEmpty()) {
            String present = dq.poll();
            int emptyValueIndex = present.indexOf("9"); // 빈칸의 위치

            int x = emptyValueIndex / 3; // map 에서 행 위치
            int y = emptyValueIndex % 3; // 열 위치

            /* 상하좌우 이동 */
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int swapIdx = nx * 3 + ny; // 2차원 인덱스를 1차원으로 변환

                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {
                    StringBuilder next = new StringBuilder(present);

                    /* change */
                    char ch = next.charAt(swapIdx);
                    next.setCharAt(swapIdx, '9');
                    next.setCharAt(emptyValueIndex, ch);

                    // 정답 찾음
                    if (next.toString().equals("123456789")) {
                        return map.get(present) + 1;
                    }

                    // 새로 만들어진 문자열인 경우
                    if(!map.containsKey(next.toString())) {
                        dq.offer(next.toString()); // 큐에 넣어 나중에 다시 체크
                        map.put(next.toString(), map.get(present) + 1);
                    }
                }
            }
        }

        return -1;
    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String temp = String.valueOf(sc.nextInt());

                if(temp.equals("0")) {
                    start.append("9");
                }
                else
                    start.append(temp);
            }
        }
    }
}
