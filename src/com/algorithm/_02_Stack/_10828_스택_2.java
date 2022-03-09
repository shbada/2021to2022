package com.algorithm._02_Stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @Date 2021
 * @URL https://www.acmicpc.net/problem/10828
 */
public class _10828_스택_2 {
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String input = "";

        while(n-- > 0) {
            input = br.readLine();

            if (input.toLowerCase().contains("push")) {
                push(Integer.parseInt(input.split(" ")[1]));
            } else if (input.toLowerCase().contains("pop")) {
                System.out.println(pop());
            } else if (input.toLowerCase().contains("size")) {
                System.out.println(size());
            } else if (input.toLowerCase().contains("empty")) {
                System.out.println(empty());
            } else if (input.toLowerCase().contains("top")) {
                System.out.println(top());
            } else {
                return;
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
        stack.push(x);
    }

    /**
     * pop
     * @return
     */
    static Integer pop() {
        if (stack.isEmpty()) { /* 비어있을 경우 */
            return -1;
        } else {
            /* 리스트이 맨 마지막 요소 */
            return stack.pop();
        }
    }

    /**
     * get size
     * @return
     */
    static Integer size() {
        return stack.size();
    }

    /**
     * isEmpty
     * @return
     */
    static Integer empty() {
        if (stack.size() == 0) {
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
        if (stack.isEmpty()) {
            return -1;
        }

        return stack.peek();
    }
}
