package com.algorithm._01_그리디_구현;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2022/05/10
 * @URL https://programmers.co.kr/learn/courses/30/lessons/81301?language=java
 */
public class P81301_숫자_문자열과_영단어 {
    static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(0, "zero");
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
    }

    public static void main(String[] args) {
        // write your code here
        P81301_숫자_문자열과_영단어 main = new P81301_숫자_문자열과_영단어();
        System.out.println(main.solution("one4seveneight"));
    }

    public int solution(String s) {
        int answer = 0;

        for (Integer key : map.keySet()) {
            if (s.contains(map.get(key))) {
                s = s.replaceAll(map.get(key), String.valueOf(key));
            }
        }

        answer = Integer.parseInt(s);
        return answer;
    }
}
