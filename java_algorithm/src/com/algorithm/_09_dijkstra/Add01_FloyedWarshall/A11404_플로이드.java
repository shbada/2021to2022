package com.algorithm._09_dijkstra.Add01_FloyedWarshall;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/11404
 */
public class A11404_플로이드 {
    /* 무한을 의미 */
    public static final int INF = (int) 1e9;

    /* 노드의 개수 */
    public static int n;

    /* 간선의 개수 */
    public static int m;

    /* 2차원 배열(그래프 표현)를 만들기 */
    public static int[][] graph = new int[501][501];

    public static void main(String[] args) {
        // write your code here
        A11404_플로이드 main = new A11404_플로이드();
        main.solution();
    }

    public void solution() {
        input();

        /* 점화식에 따라 플로이드 워셜 알고리즘을 수행 O(N^3) */
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        /* 수행된 결과를 출력 */
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (graph[a][b] == INF) { /* 도달할 수 없는 경우, 무한(INFINITY)이라고 출력 */
                    System.out.print("0 ");
                } else { /* 도달할 수 있는 경우 거리를 출력 */
                    System.out.print(graph[a][b] + " ");
                }
            }

            System.out.println();
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        for (int i = 0; i < 501; i++) {
            Arrays.fill(graph[i], INF);
        }

        /* 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화 */
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        /* 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화 */
        for (int i = 0; i < m; i++) {
            // A에서 B로 가는 비용은 C라고 설정
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (graph[a][b] != 0) {
                graph[a][b] = Math.min(graph[a][b], c);
            } else {
                graph[a][b] = c;
            }
        }
    }
}
