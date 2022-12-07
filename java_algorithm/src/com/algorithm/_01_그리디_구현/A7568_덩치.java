package com.algorithm._01_그리디_구현;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Date 2022/07/31
 * @URL https://www.acmicpc.net/problem/7568
 */
/*
    몸무게 x, 키 y -> (x, y)

    사람 A (56, 177)
    사람 B (45, 165)

    x > p, y > q 라면 사람 A가 사람 B보다 덩치가 크다.

    덩치끼리 크기를 정할 수 없는 경우
    사람 C (34, 181)
    사람 D (55, 173)

    몸무게는 사람 D가 더 무겁고, 키는 사람 C가 더 크므로 덩치의 크기를 정할 수 없다.

    N명의 집단에서 각 사람의 덩치 등수는 자신보다 더 "큰 덩치"의 사람의 수로 정해진다.
    만일 자신보다 더 큰 덩치의 사람이 k명이라면 그 사람의 덩치 등수는 k+1이 된다.
 */
public class A7568_덩치 {
    static List<List<Integer>> list = new ArrayList<>();
    static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) {
        // write your code here
        A7568_덩치 main = new A7568_덩치();
        main.solution();
    }

    public void solution() {
        input();

        /* 리스트 순회 */
        for (int i = 0; i < list.size(); i++) {
            // 현재 X, Y
            int currentX = list.get(i).get(0);
            int currentY = list.get(i).get(1);

            int n = 0;
            // 비교할 X, Y
            for (int j = 0; j < list.size(); j++) {
                // i 와 j가 같은 경우는 동일한 사람으로 판단
                if (i == j) {
                    continue;
                }

                int nextX = list.get(j).get(0);
                int nextY = list.get(j).get(1);

                // 덩치가 크다면 n + 1
                if (currentX < nextX && currentY < nextY) {
                    n++;
                }
            }

            resultList.add(n);
        }

        /* 결과 출력 ex) 2 2 1 2 2 5 */
        resultList
                .stream()
                .map(integer -> integer + 1 + " ")
                .forEach(System.out::print);
    }

    private void input() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String person = sc.nextLine();

            List<Integer> current = new ArrayList<>();
            current.add(Integer.valueOf(person.split(" ")[0]));
            current.add(Integer.valueOf(person.split(" ")[1]));

            list.add(current);
        }
    }
}
