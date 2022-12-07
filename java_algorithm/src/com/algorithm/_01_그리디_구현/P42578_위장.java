package com.algorithm._01_그리디_구현;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022/03/27
 * @URL https://programmers.co.kr/learn/courses/30/lessons/42578
 */
public class P42578_위장 {
    Map<String, List<String>> map = new HashMap<>();

    public static void main(String[] args) {
	    // write your code here
        P42578_위장 main = new P42578_위장();

        //String[][] a = new String[][] { {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] b = new String[][] { {"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        //System.out.println(main.solution(a));
        System.out.println(main.solution(b));
    }

    public int solution(String[][] clothes) {
        int answer = 1;

        for (String[] targetArr : clothes) {
            // key 가 이미 포함되어있을 경우
            if (map.containsKey(targetArr[1])) {
                map.get(targetArr[1]).add(targetArr[0]);
            } else {
                // 처음 등록되는 경우 "0"을 함께 등록한다.
                List<String> param = new ArrayList<>();
                param.add("0");
                param.add(targetArr[0]);

                map.put(targetArr[1], param);
            }
        }

        /* 최종 결과는 key(옷의 종류)에 해당하는 value 의 개수를 곱한 후 -1 */
        for (String key : map.keySet()) {
            answer *= map.get(key).size();
        }

        return answer - 1;
    }
}
