package com.algorithm._05_BFS.Dont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Date 2022/05/22
 * @URL https://www.acmicpc.net/problem/2251
 */
public class A2251_물통 {
    static int[] limit;
    static int[] bucket;

    static boolean[][] check;

    static Set<Integer> answer = new TreeSet<>();

    static class Bucket{
        int[] tmp;

        public Bucket(int[] tmp) {
            this.tmp = new int[3];

            // 복사
            System.arraycopy(tmp, 0, this.tmp, 0, 3);
        }

        Bucket move(int from, int to) {
            int[] ttmp = {tmp[0], tmp[1], tmp[2]};

            /* from + to에 담긴 물의 양이 to의 전체 용량보다 클 경우, 그러면 from에 있는 모든 양을 옮길 수 없기 때문에 부분만 이동한다. */
            if (tmp[from] + tmp[to] > limit[to]) {
                ttmp[from] -= limit[to] - tmp[to]; // from에 있는 물의 양을 (to전체용량 - to에 담긴 물의 양)만큼 옮긴다.
                ttmp[to] = limit[to]; // to에는 물이 꽉차게 된다.
            } else { /* 그 외의 경우, from에 있는 모든 물의 양을 to로 옮겨주면 된다. */
                ttmp[from] = 0; // to에 from에 있는 물의 양을 전부 옮겨준다.
                ttmp[to] = tmp[from] + tmp[to]; // from의 물통은 비게 된다.
            }

            return new Bucket(ttmp);
        }

    }

    public static void main(String[] args) throws IOException {
        A2251_물통 solution = new A2251_물통();
        solution.solution();
    }

    public void solution() throws IOException {
        input();

        bfs();

        answer.stream()
                .mapToInt(num -> num)
                .mapToObj(num -> num + " ")
                .forEach(System.out::print);
    }

    private void bfs() {
        Queue<Bucket> q = new LinkedList<>();
        q.add(new Bucket(new int[] {0, 0, limit[2]}));
        check[0][0] = true;

        while (!q.isEmpty()) {
            Bucket p = q.poll();

            // 1번 물의 양이 비게될 경우, 3번 물의 양을 저장해준다.
            if (p.tmp[0] == 0) {
                answer.add(p.tmp[2]);
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i != j) {
                        Bucket nxt = p.move(i, j);

                        // 방문 여부는 1번, 2번의 물의 양으로 체크
                        // 1, 2번에 담겨져있는 물의 양에 따라 3번 물의 양이 자동으로 정해지기 때문
                        if (!check[nxt.tmp[0]][nxt.tmp[1]]) {
                            check[nxt.tmp[0]][nxt.tmp[1]] = true;
                            q.add(nxt);
                        }
                    }
                }
            }
        }
    }

    private void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        limit = new int[3];
        bucket = new int[3];
        check = new boolean[201][201];

        for (int i = 0; i < 3; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }
    }
}
