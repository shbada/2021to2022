package com.algorithm._01_그리디_구현;

import java.util.Scanner;

/**
 * @Date 2022/09/06
 *
 * 대소문자 변환
 * 대뭔자와 소문자가 같이 존재하는 문자열을 입력받고,
 * 대문자 -> 소문자
 * 소문자 -> 대문자로 변환하여 출력
 */
public class LC2_대소문자_변환 {
    static String target;

    public static void main(String[] args) {
        // write your code here
        LC2_대소문자_변환 main = new LC2_대소문자_변환();
        main.solution();
    }

    public void solution() {
        input();

        StringBuilder result = new StringBuilder();

        // CASE 1
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);

            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            } else {
                result.append(Character.toUpperCase(c));
            }
        }

        // CASE 2
//        for (char c : target.toCharArray()) {
//            if (Character.isUpperCase(c)) {
//                result.append(Character.toLowerCase(c));
//            } else {
//                result.append(Character.toUpperCase(c));
//            }
//        }

        // CASE 3 (아스키)
        /*
           대문자 : 65 ~ 90
           소문자 : 97 ~ 122

           -> 둘의 차이는 32
         */
        for (char c : target.toCharArray()) {
            if (c >= 97 && c <= 122) { // char 은 >=을 쓰면 정수로 판단된다. (char은 원래 정수형)
                result.append((char) (c - 32)); // 대문자로
            } else {
                result.append((char) (c + 32));
            }
        }

        System.out.println(result);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        target = sc.next();
    }
}
