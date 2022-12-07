package com.algorithm._03_Queue.Dont;

import java.util.*;

/**
 * @Date 2022/03/26
 * @URL https://programmers.co.kr/learn/courses/30/lessons/42583
 */
public class P42583_다리를_지나는_트럭 {
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
	    // write your code here
        P42583_다리를_지나는_트럭 main = new P42583_다리를_지나는_트럭();
        System.out.println(main.solution(2, 10, new int[]{7, 4, 5, 6}));
        //System.out.println(main.solution(100, 100, new int[]{10}));
        //System.out.println(main.solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
        int sum = 0;
        int time = 0;

        /**
         * 다리에 트럭을 넣는 조건
         * 1) 큐가 비어있는 경우
         * 2) 큐가 가득 차지 않은 경우
         *  2-1) 트럭을 추가했을때 다리의 길이를 초과하는 경우 -> 0 을 추가해주고 시간 +1
         *  2-2) 그렇지 않은 경우 -> 추가한 트럭 무게를 sum 에 더해주고 시간을 +1
         * 3) 큐가 가득 찬 경우 ( queue 가 다리 길이만큼 꽉찼을때 )
         * -> 맨 앞의 트럭을 제거하고, sum 에서 트럭 무게만큼 뺀다.
         */

        // 배열만큼 반복
        for (int truck : truck_weights) {
            // 현재 트럭
            while (true) {
                /* 큐가 빌때까지 while 문 반복 */
                if (queue.isEmpty()) {
                    // queue add truck
                    queue.add(truck);
                    sum += truck; // 무게 sum
                    ++time; // 초
                    break;
                } else if (queue.size() == bridge_length) { // queue 가 다리 길이만큼 꽉찼을때
                    sum -= queue.poll(); // 트럭을 뻈으므로 무게 줄이기
                } else {
                    // 다리위의 무게가 꽉 차기전
                    if (sum + truck <= weight) {
                        queue.add(truck);
                        sum += truck;
                        ++time;
                        break;
                    } else {
                        /* 다리 무게가 꽉 찼을때 */
                        queue.add(0);
                        ++time;
                    }
                }

            }
        }

        return time + bridge_length; // 마지막 트럭이 다리를 모두 건너는 시간까지 더한다.
    }
}
