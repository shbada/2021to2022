package Y20_Graph;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 개선된 서로소 집합 (경로압축)
 */
public class Disjoint2 {
    public static int v; // 노드의 개수(V)
    public static int e; // 간선(Union 연산)의 개수(E)

    // 노드의 개수는 최대 100,000개라고 가정
    public static int[] parent = new int[100001]; // 부모 테이블 초기화하기

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

    /*
     6 4
     1 4
     2 3
     2 4
     5 6
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        v = sc.nextInt();
        e = sc.nextInt();

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        // Union 연산을 각각 수행
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            unionParent(a, b);
        }

        // 각 원소가 속한 집합 출력하기
        System.out.print("각 원소가 속한 집합: ");

        IntStream.rangeClosed(1, v)
                .mapToObj(i -> findParent(i) + " ")
                .forEach(System.out::print);

        System.out.println();

        // 부모 테이블 내용 출력하기
        System.out.print("부모 테이블: ");

        IntStream.rangeClosed(1, v)
                .mapToObj(i -> parent[i] + " ")
                .forEach(System.out::print);

        System.out.println();
    }
}
