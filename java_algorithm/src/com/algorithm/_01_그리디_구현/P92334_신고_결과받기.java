package com.algorithm._01_그리디_구현;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2022/05/23
 * @URL https://programmers.co.kr/learn/courses/30/lessons/92334
 */
public class P92334_신고_결과받기 {
    public static void main(String[] args) {
	    // write your code here
        P92334_신고_결과받기 main = new P92334_신고_결과받기();
        System.out.println(Arrays.toString(main.solution(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2)));

        System.out.println(Arrays.toString(main.solution(new String[]{"con", "ryan"},
                new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                3)));
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> cntMap = new HashMap<>();

        // 한 유저가 같은 유저를 여러 번 신고한 경우는 1번으로 처리해야한다.
        // String[] report 를 중복을 없앤 List 객체로 변경하자.
        /* Array to List */
        List<String> reportList = Arrays
                .stream(report)
                .distinct() // 중복제거
                .collect(Collectors.toList());

        for (String value : reportList) {
            String toName = value.split(" ")[1]; // 신고당한사람

            // 신고당한사람(key)에 대한 신고당한횟수(value) 저장
            // frodo 2, neo 2, muzi 1
            if (cntMap.containsKey(toName)) {
                cntMap.put(toName, cntMap.get(toName) + 1);
            } else {
                cntMap.put(toName, 1);
            }
        }

        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            // muzi 0, frodo 1, apeach 2, neo 3
            indexMap.put(id_list[i], i);
        }

        for (Iterator<String> iter = reportList.iterator(); iter.hasNext();) {
            String s = iter.next();
            String fromName = s.split(" ")[0]; // 신고자
            String toName = s.split(" ")[1]; // 신고당한사람

            if (cntMap.get(toName) >= k) { // 신고당한사람의 횟수가 k 이상일때
                // indexMap.get(신고자명)의 value 가 index이고 그곳에 +1로 횟수를 저장한다.
                answer[indexMap.get(fromName)] = answer[indexMap.get(fromName)] + 1;
            }

            iter.remove();
        }

        return answer;
    }
}
