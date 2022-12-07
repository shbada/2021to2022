package com.algorithm._01_그리디_구현.Dont;

import java.util.*;

/**
 * @Date 2022/06/01
 * @URL https://www.acmicpc.net/problem/13322
 */
public class A13322_접두사배열_메모리초과2 {
    static String s;

    public static void main(String[] args) {
        // write your code here
        A13322_접두사배열_메모리초과2 main = new A13322_접두사배열_메모리초과2();
        main.solution();
    }

    public void solution() {
        input();

        String[] arr = new String[s.length()];

        /*
        규칙 찾음
        1) i = 0일때 문자열 6개 (= s.length())
        2) i = 1일때 문자열 5개 (= s.length() - 1)
        3) i = 2일때 문자열 4개 (= s.length() - 2)
        4) i = 3일때 문자열 3개 (= s.length() - 3)
        5) i = 4일때 문자열 4개 (= s.length() - 4)
        6) i = 5일때 문자열 5개 (= s.length() - 5)
         */
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.substring(i);
        }

        // 정렬
        Arrays.sort(arr);

//        String param = s;
//        while (param.length() > 0) {
//            pq.add(param);
//            param = param.substring(1);
//        }

        StringBuilder sb = new StringBuilder();

        for (String value : arr) {
            int targetLength = value.length();
            sb.append(s.length() - targetLength).append("\n");
        }

        System.out.println(sb);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
    }
}
