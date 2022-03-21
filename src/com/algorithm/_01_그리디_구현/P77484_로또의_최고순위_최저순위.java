package com.algorithm._01_그리디_구현;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * @Date 2022/03/21
 * @URL https://programmers.co.kr/learn/courses/30/lessons/77484
 */
public class P77484_로또의_최고순위_최저순위 {
    public static void main(String[] args) {
	    // write your code here
        P77484_로또의_최고순위_최저순위 main = new P77484_로또의_최고순위_최저순위();
        System.out.println(Arrays.toString(main.solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19})));
        System.out.println(Arrays.toString(main.solution2(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19})));
    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];

        /* key : 맞는 번호 개수, value : 등수 */
        Map<Integer, Integer> map = new HashMap<>();
        map.put(6, 1);
        map.put(5, 2);
        map.put(4, 3);
        map.put(3, 4);
        map.put(2, 5);
        map.put(1, 6);
        map.put(0, 6);

        /* 지워진 0의 개수에 따라 계산된다. */
        long zeroCount = Arrays.stream(lottos)
                .filter(i -> i == 0)
                .count();

        /* 당첨된 번호의 개수를 구한다. */
        int sameCount = 0;
        for (int lotto : lottos) {
            for (int win_num : win_nums) {
                if (lotto == win_num) {
                    sameCount++;
                }
            }
        }

        /*
           최고등수 : 0이 모두 당첨일때의 경우
           -> 0의 개수 + 이미 당첨된 번호의 개수

           최저등수 : 0이 모두 미당첨일때의 경우
           -> 이미 당첨된 번호의 개수
         */
        answer[0] = map.get((int) (zeroCount + sameCount));
        answer[1] = map.get(sameCount);

        return answer;
    }

    /**
     * 다른 사람의 풀이
     * @param lottos
     * @param winNums
     * @return
     */
    public int[] solution2(int[] lottos, int[] winNums) {
        return LongStream.of(
                        /* 1) 당첨된 번호 개수 + 0의 개수 */
                        (lottos.length + 1) - Arrays.stream(lottos)
                                .filter(l -> Arrays.stream(winNums).anyMatch(w -> w == l) || l == 0).count(),
                        /* 2) 당첨된 번호 개수 */
                        (lottos.length + 1) - Arrays.stream(lottos)
                                .filter(l -> Arrays.stream(winNums).anyMatch(w -> w == l)).count()
                )
                .mapToInt(op -> (int) (op > 6 ? op - 1 : op))
                .toArray();
    }
}
