package com.algorithm._00_빠른문법;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class _02_Scanner {
    /**
     * N 입력받고 개수만큼 한줄로 받아 array에 담기
     * @throws IOException
     */
    void input1() throws IOException {
        Scanner sc = new Scanner(System.in);

        String param = sc.nextLine(); // 3

        int N = Integer.parseInt(param.split(" ")[0]);
        int[] arr = new int[N];

        /* 집합 S */
        String S = sc.nextLine(); // 5 1 2

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(S.split(" ")[i]);
        }
    }

    /**
     * N, K를 한 줄에 입력받고 N개 만큼 한줄씩 입력받아 Stack에 담기
     * @throws IOException
     */
    void input2() throws IOException {
        Scanner sc = new Scanner(System.in);

        String param = sc.nextLine();

        int num = Integer.parseInt(param.split(" ")[0]);
        int sum = Integer.parseInt(param.split(" ")[1]);

        Stack<Integer> stack = new Stack<Integer>();

        /* 오름차순의 경우, 제일 비싼 동전부터 체크해야햐므로 stack 사용 */
        for (int i = 0; i < num; i++) {
            Integer input = sc.nextInt();
            stack.push(input);
        }
    }

    /**
     * 2차원배열 입력받기
     * @throws IOException
     */
    void input3() throws IOException {
        /* 2차원 배열 */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String[][] arr = new String[N][2];


        for(int i = 0; i < N; i++) {
            arr[i][0] = in.next();	// 나이
            arr[i][1] = in.next();	// 이름
        }
    }
}
