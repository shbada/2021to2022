package com.algorithm._02_Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @Date 2021
 * @URL https://www.acmicpc.net/problem/10828
 */
public class A10828_스택 {
    static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String[] text = br.readLine().split(" ");

            switch (text[0]) {
                case "push":
                    push(Integer.parseInt(text[1]));
                    continue;
                case "pop":
                    System.out.println(pop());
                    continue;
                case "size":
                    System.out.println(size());
                    continue;
                case "empty":
                    System.out.println(empty());
                    continue;
                case "top":
                    System.out.println(top());
                    continue;
            }
        }
    }

    /**
     * push
     */
    static void push(Integer x) {
        if (x == null) {
            return;
        }
        arrayList.add(x);
    }

    /**
     * pop
     * @return
     */
    static Integer pop() {
        if (arrayList.isEmpty()) { /* 비어있을 경우 */
            return -1;
        } else {
            /* 리스트이 맨 마지막 요소 */
            return arrayList.remove(arrayList.size() - 1);
        }
    }

    /**
     * get size
     * @return
     */
    static Integer size() {
        return arrayList.size();
    }

    /**
     * isEmpty
     * @return
     */
    static Integer empty() {
        if (arrayList.size() == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * get top
     * @return
     */
    static Integer top() {
        if (arrayList.isEmpty()) {
            return -1;
        }

        return arrayList.get(arrayList.size() - 1);
    }
}
