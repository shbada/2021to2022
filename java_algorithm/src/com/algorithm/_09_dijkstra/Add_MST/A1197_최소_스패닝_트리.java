package com.algorithm._09_dijkstra.Add_MST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @Date 2022/04/10
 * @URL https://www.acmicpc.net/problem/1197
 */
public class A1197_최소_스패닝_트리 {
    public static int v; // 노드의 개수(V)
    public static int e; // 간선(Union 연산)의 개수(E)

    // 노드의 개수는 최대 100,000개라고 가정
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기

    // 모든 간선을 담을 리스트
    public static ArrayList<Edge> edges = new ArrayList<>();

    // 최종 비용을 담을 변수
    public static int result = 0;

    static class Edge implements Comparable<Edge> {

        private int distance;
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB) {
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        public int getDistance() {
            return this.distance;
        }

        public int getNodeA() {
            return this.nodeA;
        }

        public int getNodeB() {
            return this.nodeB;
        }

        // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
        @Override
        public int compareTo(Edge other) {
            if (this.distance < other.distance) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) {
        // write your code here
        A1197_최소_스패닝_트리 main = new A1197_최소_스패닝_트리();
        main.solution();
    }

    public void solution() {
        input();

        // 간선을 비용순으로 정렬
        Collections.sort(edges);

        // 간선을 하나씩 확인하며
        for (Edge edge : edges) {
            int cost = edge.getDistance();
            int a = edge.getNodeA();
            int b = edge.getNodeB();

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }

        System.out.println(result);
    }

    // 특정 원소가 속한 집합을 찾기
    public static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = findParent(parent[x]);
    }

    // 두 원소가 속한 집합을 합치기
    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        // 모든 간선에 대한 정보를 입력 받기
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            edges.add(new Edge(cost, a, b));
        }
    }
}
