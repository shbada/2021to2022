package com.algorithm._01_그리디_구현.Dont;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 2022/06/01
 * @URL https://www.acmicpc.net/problem/13322
 */
public class A13322_접두사배열_메모리초과 {
    static String s;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        A13322_접두사배열_메모리초과 main = new A13322_접두사배열_메모리초과();
        main.solution();
    }

    public void solution() {
        input();

        for (int i = 0; i < s.length(); i++) {
            list.add(s.substring(i));
        }

        // 정렬
        List<String> copyList = new ArrayList<>(list);
        Collections.sort(list);

        for (int i = 0; i < copyList.size(); i++) {
            System.out.println(copyList.indexOf(list.get(i)));
        }
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        s = sc.next();
    }
}
