package com.algorithm._09_dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/1753
 */
public class A1753_최단경로 {

    /* 무한을 의미하는 값으로 10억을 설정 */
    public static final int INF = (int) 1e9;

    public static int n; // 노드의 개수(N)
    public static int m; // 간선의 개수(M)
    public static int start; // 시작 노드 번호(Start)

    /* 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열 */
    public static ArrayList<ArrayList<QueueNode>> graph = new ArrayList<ArrayList<QueueNode>>();

    /* 최단 거리 테이블 만들기 */
    public static int[] d = new int[100001];

    static class QueueNode implements Comparable<QueueNode> {

        private int index;
        private int distance;

        public QueueNode(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return this.index;
        }

        public int getDistance() {
            return this.distance;
        }

        // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
        @Override
        public int compareTo(QueueNode other) {
            if (this.distance < other.distance) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) {
        // write your code here
        A1753_최단경로 main = new A1753_최단경로();
        main.solution();
    }

    public void solution() {
        input();

        dijkstra(start);

        /* 도달할 수 있는 경우 거리 출력 (1부터 시작했으므로 n + 1) */
        IntStream.rangeClosed(1, n).forEach(i -> {
            if (d[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(d[i]);
            }
        });
    }

    private static void dijkstra(int start) {
        PriorityQueue<QueueNode> pq = new PriorityQueue<>();
        /* 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입 */
        pq.offer(new QueueNode(start, 0));

        d[start] = 0;

        /* 큐가 비어있지않을 때까지 반복 */
        while(!pq.isEmpty()) {
            /* 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기 */
            QueueNode node = pq.poll();

            int dist = node.getDistance(); // 현재 노드까지의 비용
            int now = node.getIndex(); // 현재 노드 번호

            /* 현재 노드가 이미 처리된 적이 있는 노드라면 무시 */
            if (d[now] < dist) {
                continue;
            }

            /* 현재 노드와 연결된 다른 인접한 노드들을 확인 */
            for (int i = 0; i < graph.get(now).size(); i++) {
                /* 현재의 최단거리 + 현재의 연결된 노드의 비용 */
                int cost = d[now] + graph.get(now).get(i).getDistance();

                /* 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우 */
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new QueueNode(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }

    private static void input() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 노드의 개수(N)
        m = sc.nextInt(); // 간선의 개수(M)
        start = sc.nextInt(); // 시작 노드 번호(Start)

        /* 그래프 초기화 */
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<QueueNode>());
        }

        /* 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화 */
        d[start] = 0;

        /* 모든 간선 정보를 입력받기 */
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            /* a번 노드에서 b번 노드로 가는 비용이 c라는 의미 */
            graph.get(a).add(new QueueNode(b, c));
        }

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        Arrays.fill(d, INF);
    }
}
