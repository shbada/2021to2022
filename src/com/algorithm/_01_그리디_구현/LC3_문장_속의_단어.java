package com.algorithm._01_그리디_구현;

import java.util.Scanner;

/**
 * @Date 2022/09/07
 * 한개의 문장이 주어지면 그 문장 속에서 가장 긴 단어를 출력하는 프로그램
 * 문장속의 각 단어는 공백으로 구분된다.
 */
public class LC3_문장_속의_단어 {
    static String S;

    public static void main(String[] args) {
        // write your code here
        LC3_문장_속의_단어 main = new LC3_문장_속의_단어();
        main.solution();
    }

    public void solution() {
        input();

        String result = "";

//        for (String s : S.split(" ")) {
//            if (s.length() > result.length()) {
//                result = s;
//            }
//        }

//        int m = Integer.MIN_VALUE;
//        String[] s = S.split(" ");
//
//        for (String x : s) {
//            int len = x.length();
//
//            if (len > m) {
//                m = len;
//                result = x;
//            }
//        }

        /** indexOf / subString 사용 */
        int m = Integer.MIN_VALUE;
        int pos;
        String[] s = S.split(" ");

        // 띄어쓰기를 발견할 수 있을때까지 반복한다.
        while ((pos = S.indexOf(' ')) != -1) {
            String tmp = S.substring(0, pos); // 0부터 pos 전까지 (pos : 띄어쓰기 공간)

            int len = tmp.length();

            if (len > m) { // 같다 조건을 추가하면 안됨. 같을때 뒤쪽 단어로 갱신되기 때문
                m = len;
                result = tmp;
            }

            // 해당 지점부터 끝까지로 잘른다.
            // it is time to study 일때 pos는 공백이므로 공백+1 부터 문장을 잘라야한다.
            // 마지막에 to 까지 진행했을때, S 가 study로 바뀌어버린다. -> while 문을 적용하지 못하고 종료된다.
            // 마지막 단어는 별도로 체크해줘야한다.
            S = S.substring(pos + 1);
        }

        if (S.length() > m) {
            result = S;
        }

        System.out.println(result);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        S = sc.nextLine();
    }
}
