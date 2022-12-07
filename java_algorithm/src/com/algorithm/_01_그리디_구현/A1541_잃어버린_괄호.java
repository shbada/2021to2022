package com.algorithm._01_그리디_구현;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Date 2022/05/29
 * @URL https://www.acmicpc.net/problem/1541
 */
public class A1541_잃어버린_괄호 {
    static String param;

    public static void main(String[] args) {
        // write your code here
        A1541_잃어버린_괄호 main = new A1541_잃어버린_괄호();
        main.solution();
    }

    public void solution() {
        input();

        // 덧셈을 먼저하고 마지막에 뺄셈을 수행하면 될듯하다.
        // 55-50+40-60 의 경우
        // 55, 50+40, 60
        String[] minusArr = param.split("-");
        int sum = 0;

        boolean isFirst = true;
        for (String s : minusArr) {
            String[] plusArr = s.split("\\+");

            if (isFirst) {
                sum = Arrays.stream(plusArr).mapToInt(Integer::parseInt).sum();
                isFirst = false;
            } else {
                sum -= Arrays.stream(plusArr).mapToInt(Integer::parseInt).sum();
            }
        }

        System.out.println(sum);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        param = sc.next();
    }
}
