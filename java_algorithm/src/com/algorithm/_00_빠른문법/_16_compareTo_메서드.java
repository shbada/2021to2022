package com.algorithm._00_빠른문법;

import java.util.PriorityQueue;

public class _16_compareTo_메서드 {
    static class QueueNode implements Comparable<QueueNode> {

        private int index;
        private int distance;

        public QueueNode(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정 (오름차순)
//        @Override
//        public int compareTo(QueueNode other) {
//            // this.distance 가 작으면 -1 : 음수이면 앞으로 (작은게 앞이므로 오름차순)
//            if (this.distance < other.distance) {
//                return -1;
//            }
//            return 1;
//        }

        // 거리(비용)가 긴 것이 높은 우선순위를 가지도록 설정 (내림차순)
        @Override
        public int compareTo(QueueNode other) {
            // this.distance 가 크면 -1 : 큰 값(this.distance)이 앞이므로 내림차순)
            if (this.distance >= other.distance) {
                return -1;
            }
            return 1;
        }

        @Override
        public String toString() {
            return "QueueNode{" +
                    "index=" + index +
                    ", distance=" + distance +
                    '}';
        }
    }

//    public static void main(String[] args) {
//        ArrayList<QueueNode> list = new ArrayList<>();
//        list.add(new QueueNode(0, 5));
//        list.add(new QueueNode(1, 1));
//        list.add(new QueueNode(2, 2));
//        list.add(new QueueNode(3, 3));
//
//        list.sort(QueueNode::compareTo);
//        System.out.println(list);
//    }

    public static void main(String[] args) {
        PriorityQueue<QueueNode> pq = new PriorityQueue<>();

        // compareTo
        pq.add(new QueueNode(0, 5));
        pq.add(new QueueNode(1, 1));
        pq.add(new QueueNode(2, 2));
        pq.add(new QueueNode(3, 3));

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}
