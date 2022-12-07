package com.algorithm._06_BinarySearch;

/*
동빈이네 전자 매장에는 부품이 N개 있다. 각 부품은 정수 형태의 고유한 번호가 있다. 어느 날 손님이 M개의 종류의



부품을 대량으로 구매하겠다며 당일 날 견적서를 요청했다. 동빈이는 때를 놓치지 않고 손님이 문의한 부품 M개



종류를 모두 확인해서 경적서를 작성해야 한다. 이때 가게 안에 부품이 모두 있는지 확인하는 프로그램을 작성해보자.



예를 들어 가게의 부품이 총 5개일 때 부품 번호가 다음과 같다고 하자.



N = 5
[8, 3, 7, 9, 2]


손님은 총 3개의 부품이 있는지 확인 요청했는데 부품 번호는 다음과 같다.



M = 3
[5, 7, 9]


이때 손님이 요청한 부품 번호의 순서대로 부품을 확인해 부품이 있으면 yes를, 없으면 no를 출력한다. 구분은 공백으로 한다.
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @Date 2022/03/20
 * @URL 이것이코딩테스트다 chapter05
 */
public class Tchapter05_부품찾기 {
    static int N; // 부품 개수
    static int[] arr; // 부품 존재 배열

    static int M; // 찾을 부품 개수
    static int[] findArr; // 찾을 부품 배열

    public static void main(String[] args) {
	    // write your code here
        Tchapter05_부품찾기 main = new Tchapter05_부품찾기();
        main.solution();
    }

    public void solution() {
        input();

        binarySearch();
    }

    /**
     * 이진탐색을 사용한 방법
     */
    private static void binarySearch() {
        /* asc sort */
        Arrays.sort(arr);

        for (int target : findArr) {
            /* start, end set */
            int start = 0;
            int end = arr.length - 1;

            boolean isFind = false; // find flag

            while (start <= end) {
                /* 중간점 찾기 */
                int mid = (start + end) / 2;

                if (arr[mid] == target) { /* 찾은 경우 flag true */
                    isFind = true;
                    break;
                } else if (arr[mid] > target) { /* 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인 */
                    end = mid - 1;
                } else {
                    start = mid + 1; /* 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인 */
                }
            }

            System.out.print(isFind ? "yes " : "no ");
        }
    }

    /**
     5
     8 3 7 9 2
     3
     5 7 9
     */
    private static void input() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];

        /* 부품 존재 배열 */
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        M = sc.nextInt();
        findArr = new int[M];

        /* 찾을 부품 배열 */
        for (int i = 0; i < M; i++) {
            findArr[i] = sc.nextInt();
        }
    }

    /**
     * 리스트를 사용한 방법
     */
    private static void findList() {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());

        Arrays.stream(findArr).forEach(n -> {
            if (list.contains(n)) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        });
    }
}
