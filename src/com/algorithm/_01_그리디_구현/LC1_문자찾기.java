package com.algorithm._01_그리디_구현;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * [입력]
 * 첫줄에 문자열이 주어지고, 두번째 줄에 문자가 주어진다.
 * 문자열은 영어 알파벳으로만 구성되어있다.
 *
 * [출력]
 * 첫줄에 해당 문자의 개수를 출력한다.
 * @Date 2022/09/05
 */
public class LC1_문자찾기 {
    static String target;
    static char findTarget;

    public static void main(String[] args) {
        // write your code here
        LC1_문자찾기 main = new LC1_문자찾기();
        main.solution();
    }

    public void solution() {
        input();

        int count = (int) IntStream.range(0, target.toUpperCase().length())
                .filter(i -> target.charAt(i) == Character.toUpperCase(findTarget))
                .count();

        System.out.println(count);

    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        target = sc.next(); // 문자열
        // char 타입으로 받기
        findTarget = sc.next().charAt(0);
    }
}
