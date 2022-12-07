package Y19_ShortestPath.PriorityQueue;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // PriorityQueue 를 사용해서 숫자가 작을수록 우선순위를 가지게 저장
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        int[] arr = new int[]{5, 7, 3, 1, 2};

        for (int v : arr) {
            pq.add(v);
        }

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
