package com.algorithm._01_그리디_구현.Dont;

/**
 * @Date 2022/03/23
 * @URL https://programmers.co.kr/learn/courses/30/lessons/42860
 */
public class P42860_조이스틱 {
    static int cursor = 0;

    public static void main(String[] args) {
	    // write your code here
        P42860_조이스틱 main = new P42860_조이스틱();
        System.out.println(main.solution("JAZ"));
    }

    public int solution(String name) {
        int answer = 0;
        int LR = name.length() - 1; //좌우 이동의 최대횟수

        for(int i = 0; i < name.length(); i++) {
            // 위아래 조작 비교
            char c = name.charAt(i);
            // 아스키 코드 값 - 65 (A = 0 ... Z = 25)
            // 위로 조작했을 때와 아래로 조작했을 때중 작은 이동 값을 answer에 추가
            answer += Math.min(c - 65, 26 - (c - 65));

            // 좌우 조작 비교
            // 다음 요소 부터 연속된 A문자열 탐색
            int indexA = i + 1;
            while(indexA < name.length() && name.charAt(indexA) == 'A') {
                indexA++;
            }

            // i+i > 연속된 A문자열이 시작하기 전까지 갔다가 되돌아옴
            // name.len
            LR = Math.min(LR, i + i + name.length() - indexA);
        }

        return answer + LR;
    }
}
