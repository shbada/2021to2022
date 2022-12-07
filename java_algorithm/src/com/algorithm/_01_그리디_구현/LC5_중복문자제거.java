package com.algorithm._01_그리디_구현;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Date 2022/11/21
 */
public class LC5_중복문자제거 {
    static String str;

    public static void main(String[] args) {
        // write your code here
        LC5_중복문자제거 main = new LC5_중복문자제거();
        System.out.println(main.solution());
        System.out.println(main.lecture_solution());
    }

    public String solution() {
        input();

        List<Character> result = new ArrayList<>();

        IntStream.range(0, str.length())
                .filter(i -> !result.contains(str.charAt(i)))
                .forEach(i -> result.add(str.charAt(i)));

        return result.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));

    }

    /*
        ksekkset
        k 0 0
        s 1 1 // 이렇게 같으면 첫번째로 등장한 문자
        e 2 2
        k 3 0 // 자기 자신은 index 3인데, 0 이라는건 중복문자라는 것이다.
        k 4 0
        s 5 1
        e 6 2
        t 7 7
     */
    public String lecture_solution() {
        input();

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            /* indexOf(..) : str.charAt(i) 이 문자의 첫번째 index */
            System.out.println(str.charAt(i) + " " + i + " " + str.indexOf(str.charAt(i)));

            if (str.indexOf(str.charAt(i)) == i) { // 같으면 첫번째로 발견된 문자다.
                answer.append(str.charAt(i));
            }
        }

        return answer.toString();
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        str = sc.next();
    }
}
