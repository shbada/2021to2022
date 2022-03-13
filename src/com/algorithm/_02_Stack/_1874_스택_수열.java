package com.algorithm._02_Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @Date 2022.03.12
 * https://www.acmicpc.net/problem/1874
 */
public class _1874_스택_수열 {
    static int N;
    static int index = 1;
    static Queue<Integer> paramQueue = new LinkedList<>();
    static List<String> resultList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        input();

        /* stack 선언 */
        Stack<Integer> stack = new Stack<>();

        while(!paramQueue.isEmpty()) {
            if (stack.isEmpty()) {
                stack.push(index++);
                resultList.add("+");
            } else if (Objects.equals(stack.peek(), paramQueue.peek())) {
                stack.pop();
                paramQueue.poll();

                resultList.add("-");
            } else {
                if (index > N) {
                    resultList.clear();
                    break;
                } else if (index >= stack.peek()) {
                    stack.push(index++);
                    resultList.add("+");
                }
            }
        }

        if (resultList.isEmpty()) {
            System.out.println("NO");
        } else {
            resultList.forEach(System.out::println);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            paramQueue.offer(Integer.parseInt(br.readLine()));
        }
    }
}
