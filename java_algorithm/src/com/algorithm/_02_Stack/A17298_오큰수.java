package com.algorithm._02_Stack;

import java.util.*;

/**
 * @Date 2022/05/28
 * @URL https://www.acmicpc.net/problem/17298
 */
public class A17298_오큰수 {
    static int N;
    static int[] arr;

    public static void main(String[] args) {
        // write your code here
        A17298_오큰수 main = new A17298_오큰수();
        main.solution();
    }

    public void solution() {
        input();

//        for (int i = 1; i < arr.length; i++) {
//            int target = arr[i];
//
//            int answer = -1;
//            for (int j = i + 1; j < arr.length; j++) {
//                if (target < arr[j]) {
//                    answer = arr[j];
//                    break;
//                }
//            }
//
//            System.out.print(answer + " ");
//        }

//        for (int i = 1; i < arr.length; i++) {
//            int target = arr[i];
//
//            int first = Arrays.stream(arr, i + 1, arr.length)
//                    .filter(index -> target < index)
//                    .findFirst()
//                    .orElse(-1);
//
//            System.out.print(first + " ");
//        }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);

        for (int i = 2; i < arr.length; i++) {
            while (!stack.isEmpty()) {
                if (arr[stack.peek()] < arr[i]) {
                    arr[stack.pop()] = arr[i];
                } else {
                    break;
                }
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }

//        IntStream.range(1, arr.length)
//                .mapToObj(i -> arr[i] + " ")
//                .forEach(System.out::print);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N + 1];

        /* 인접행렬 생성 */
        for (int i = 1; i < N + 1; i++) {
            arr[i] = sc.nextInt();
        }
    }
}
