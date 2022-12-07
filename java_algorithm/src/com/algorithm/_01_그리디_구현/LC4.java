package com.algorithm._01_그리디_구현;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Date 2022/09/08
 * 단어 뒤집기
 * N개의 단어가 주어지면 각 단어를 뒤집어 출력하는 프로그램
 */
public class LC4 {
    static int N;
    static String[] targets;

    public static void main(String[] args) {
        // write your code here
        LC4 main = new LC4();
        main.solution();
    }

    public void solution() {
        input();

        for (String target : targets) {
            StringBuilder result = new StringBuilder();

            for (int i = target.length() - 1; i >= 0; i--) {
                result.append(target.charAt(i));
            }

            System.out.println(result);
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        targets = new String[N];

        for (int i = 0; i < N; i++) {
            targets[i] = sc.next();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Main T = new Main();

        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();

        String[] str = new String[n];

        for (int i = 0; i < n; i++) {
            str[i] = kb.next();
        }

        for (String x : T.solution(n, str)) {
            System.out.println(x);
        }
    }

    private ArrayList<String> solution(int n, String[] str) {
        ArrayList<String> answer = new ArrayList<>();

//        for (String x : str) {
//            String tmp = new StringBuilder(x).reverse().toString();
//            answer.add(tmp);
//        }

        // 다른 방법 (GOOD 일때 G - D, O - O 바꾸면 됨)
        for (String x : str) {
            char[] s = x.toCharArray();

            int lt = 0;
            int rt = x.length() - 1;

            while (lt < rt) {
                // 값 교환
                char tmp = s[lt];
                s[lt] = s[rt];
                s[rt] = tmp;

                lt++;
                rt--;
            }

            String tmp = String.valueOf(s); // static 메서드
            answer.add(tmp);
        }

        return answer;
    }

}