package com.algorithm._01_그리디_구현;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P42885_구명보트 {

    public static void main(String[] args) {
        // write your code here
        P42885_구명보트 main = new P42885_구명보트();
        //System.out.println(main.solution(new int[]{70, 50, 80, 50}, 100));
        //System.out.println(main.solution(new int[]{70, 80, 50}, 100));
        //System.out.println(main.solution(new int[]{130, 80, 50, 30}, 100));
        //System.out.println(main.solution(new int[]{160, 150, 140, 60, 50, 40}, 200));
        System.out.println(main.solution(new int[]{200, 170, 150, 50, 40, 20}, 100));
    }

    public int solution(int[] people, int limit) {
        int answer = 0;

        // 오름차순 정렬
        Arrays.sort(people);

        // 거꾸로간다.
        int start = people.length - 1; // 시작원소
        int end = 0; // 마지막원소

        /** 맨 앞의 사람이 보트 제한 무게의 절반 이하가 되면, 무조건 맨 뒤의 사람과 같이 보낼 수 있다.
            절반의 값이 limit 보다 작거나 같으면, 그 다음부터는 해당 원소보다 무조건 작기 때문이다. */
        while (end <= start) {
            if (people[start] <= limit / 2) {
                int target = (start - end + 1);
                answer += (target / 2) + (target % 2);
                return answer;
            }

            int sum = people[start] + people[end];

            if (sum > limit) {
                answer++; // start 원소 1개로 개수 +1

                start--; // 앞부분 + 1
            } else {
                // cursor 이동
                start--;
                end++;

                answer++;
            }
        }

        return answer;
    }

    /**
     * List 로 해결 - 시간초과
     * @param people
     * @param limit
     * @return
     */
    public int solution_3(int[] people, int limit) {
        int answer = 0;

        // 내림차순 정렬
        List<Integer> store = Arrays.stream(people).boxed()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());


        int start = 0; // 첫번째 원소
        int end = store.size() - 1; // 마지막 원소

        /** 맨 앞의 사람이 보트 제한 무게의 절반 이하가 되면, 무조건 맨 뒤의 사람과 같이 보낼 수 있다.
         절반의 값이 limit 보다 작거나 같으면, 그 다음부터는 해당 원소보다 무조건 작기 때문이다. */
        while (start <= end) {
            if (store.get(start) <= limit / 2) {
                int target = (end - start + 1);
                answer += (target / 2) + (target % 2);
                return answer;
            }

            int sum = store.get(start) + store.get(end);

            if (sum > limit) {
                answer++; // start 원소 1개로 개수 +1

                start++; // 시작 원소만 +1
            } else {
                // cursor 이동
                start++;
                end--;

                answer++;
            }
        }

        return answer;
    }

    /**
     * 문제 잘못 이해 - 2명씩인줄 모르고 구현
     * @param people
     * @param limit
     * @return
     */
    public int solution_2(int[] people, int limit) {
        int answer = 0;

        List<Integer> store = new ArrayList<>();

        // 내림차순 정렬
        Integer[] arr3 = Arrays.stream(people).boxed().toArray(Integer[]::new);
        Arrays.sort(arr3, Collections.reverseOrder());

        // store 에 원소 저장
        Collections.addAll(store, arr3);

        // sum 계산
        int cursor = store.size() - 1;

        // store 가 비어있지 않을때까지 반복
        while (store.size() > 0) {

            if (getSum(store, cursor) > limit) { // limit 충족하지 않음
                // 커서 이동
                cursor--;
            } else { // limit 충족함
                answer++;

                // 현재 cursor 까지의 원소 제거
                int index = cursor;
                while (index >= 0) {
                    store.remove(index);
                    index--;
                }

                // cursor 갱신
                cursor = store.size() - 1;
            }
        }


        return answer;
    }

    public int getSum(List<Integer> param, int cursor) {
        // 0 부터 cursor 까지의 합
        int sum = IntStream.rangeClosed(0, cursor)
                .map(param::get)
                .sum();

        return sum;
    }
}
