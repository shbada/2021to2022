package com.algorithm._01_그리디_구현;

/*
        행복 왕국의 왕실 정원은 체스판과 같은 8 × 8 좌표 평면이다. 왕실 정원의 특정한 한 칸에 나이트가 서있다.
        나이트는 매우 충성스러운 신하로서 매일 무술을 연마한다
        나이트는 말을 타고 있기 때문에 이동을 할 때는 L자 형태로만 이동할 수 있으며 정원 밖으로는 나갈 수 없다
        나이트는 특정 위치에서 다음과 같은 2가지 경우로 이동할 수 있다

        수평으로 두 칸 이동한 뒤에 수직으로 한 칸 이동하기
        수직으로 두 칸 이동한 뒤에 수평으로 한 칸 이동하기

        이처럼 8 × 8 좌표 평면상에서 나이트의 위치가 주어졌을 때 나이트가 이동할 수 있는 경우의 수를 출력하는
        프로그램을 작성하라. 왕실의 정원에서 행 위치를 표현할 때는 1부터 8로 표현하며, 열 위치를 표현할 때는
        a 부터 h로 표현한다

        c2에 있을 때 이동할 수 있는 경우의 수는 6가지이다
        a1에 있을 때 이동할 수 있는 경우의 수는 2가지이다

        입력
        첫째 줄에 8x8 좌표 평면상에서 현재 나이트가 위치한 곳의 좌표를 나타내는 두 문자로 구성된 문자열이 입력된다. 입력 문자는 a1 처럼 열과 행으로 이뤄진다.

        출력
        첫째 줄에 나이트가 이동할 수 있는 경우의 수를 출력하시오.

        입력 예시
        a1

        출력 예시
        2
     */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Date 2022/03/19
 * @URL 이것이 코딩테스트다 chapter04
 */
public class Tcahpter04_왕실의_나이트 {
    static String inputData;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
	    // write your code here
        Tcahpter04_왕실의_나이트 main = new Tcahpter04_왕실의_나이트();
        main.solution();
    }

    /*
      (위/아래 : x (행), 왼/오 : y(열))
      - 오른쪽 2칸, 위로 1칸
      (-1, 2)
      - 오른쪽 2칸, 아래로 1칸
      (1, 2)
      - 왼쪽으로 2칸, 위로 1칸
      (-2, -2)
      - 왼쪽으로 2칸, 아래로 1칸
      (1, -2)
      - 아래로 2칸, 오른쪽으로 1칸
      (1, 2)
      - 위로 2칸, 오른쪽으로 1칸
      (1, -2)
      - 아래로 2칸, 왼쪽으로 1칸
      (-1, 2)
      - 위로 2칸, 왼쪽으로 1칸
      (-1, -2)
     */
    public void solution() {
        input();

        // a1 (열 : a, 행 : 1)
        // 열
        int column = map.get(String.valueOf(inputData.charAt(0)));
        // 행
        int row = inputData.charAt(1) - '0';

        /* 이동범위 선언 */
        int[] dx = {0, -1, 1, -2, 1, 1, 1, -1, -1};
        int[] dy = {0, 2, 2, -2, -2, 2, -2, 2, -2};

        int result = 0;

        // 이동 가능 여부 체크
        for (int i = 1; i < map.size(); i++) {
            int nx = row + dx[i]; // 행 이동
            int ny = column + dy[i]; // 열 이동

            /* case1. 행 이동 0 보다 크고 9보다 작음 */
            /* case2. 열 이동 0 보다 크고 9보다 작음 */
            if (nx > 0 && ny < 9 && nx < 9 && ny > 0) {
                result++;
            }
        }

        System.out.println(result);
    }

    private void input() {
        /* 8개 열의 map */
        map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);

        Scanner sc = new Scanner(System.in);

        inputData = sc.nextLine();
    }
}
