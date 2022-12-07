package Y19_ShortestPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Dijkstra {
    /* 무한을 의미하는 값으로 10억을 설정 */
    public static final int INF = (int) 1e9;

    public static int n; // 노드의 개수(N)
    public static int m; // 간선의 개수(M)
    public static int start; // 시작 노드 번호(Start)

    /* 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열 */
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

    /* 방문한 적이 있는지 체크하는 목적의 배열 만들기 */
    public static boolean[] visited = new boolean[100001];

    /* 최단 거리 테이블 만들기 */
    public static int[] d = new int[100001];

    public static void main(String[] args) {
        input();

        /* 다익스트라 알고리즘을 수행 */
        dijkstra(start);

        /* 도달할 수 있는 경우 거리 출력 (1부터 시작했으므로 n + 1) */
        IntStream.range(0, n + 1).filter(i -> d[i] != INF).mapToObj(i -> d[i]).forEach(System.out::println);
    }

    private static void dijkstra(int start) {
        /* 시작 노드에 대해서 초기화 */
        d[start] = 0;

        /* 방문 처리 */
        visited[start] = true;

        /* start 노드와 연결되어있는 노드의 개수만큼 반복 */
        for (int j = 0; j < graph.get(start).size(); j++) {
            /* '연결된 노드 번호' 번째의 d 배열에 '연결된 노드 번호' 번째의 비용 저장 */
            d[graph.get(start).get(j).getIndex()] = graph.get(start).get(j).getDistance();
        }

        /* 시작 노드를 제외한 전체 n - 1개의 노드에 대해 반복 */
        for (int i = 0; i < n - 1; i++) {
            /* 현재 최단 거리가 가장 짧은 노드를 꺼내서, 방문 처리 */
            int now = getSmallestNode();
            
            /* 방문 처리 */
            visited[now] = true;
            
            /* (짧은 노드를 우선적 방문) 현재 노드와 연결된 다른 노드를 확인 */
            for (int j = 0; j < graph.get(now).size(); j++) {
                /* 현재의 최단거리 + 현재의 연결된 노드의 비용 */
                int cost = d[now] + graph.get(now).get(j).getDistance();
                
                /* 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우 */
                if (cost < d[graph.get(now).get(j).getIndex()]) {
                    d[graph.get(now).get(j).getIndex()] = cost;
                }
            }
        }

    }

    /**
     * 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환
     * @return
     */
    private static int getSmallestNode() {
        int min_value = INF;
        int index = 0; // 가장 최단 거리가 짧은 노드(인덱스)

        /* 최단거리 노드번호 구하기 */
        for (int i = 1; i <= n; i++) {
            if (d[i] < min_value && !visited[i]) {
                min_value = d[i];
                index = i;
            }
        }

        return index;

    }

    private static void input() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 노드의 개수(N)
        m = sc.nextInt(); // 간선의 개수(M)
        start = sc.nextInt(); // 시작 노드 번호(Start)

        /* 그래프 초기화 */
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        /* 모든 간선 정보를 입력받기 */
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            /* a번 노드에서 b번 노드로 가는 비용이 c라는 의미 */
            graph.get(a).add(new Node(b, c));
        }

        /* 최단 거리 테이블을 모두 무한으로 초기화 */
        Arrays.fill(d, INF);
    }
}

class Node {
    private int index; /* 노드 번호 */
    private int distance; /* 최단 경로 */

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return this.index;
    }

    public int getDistance() {
        return this.distance;
    }
}