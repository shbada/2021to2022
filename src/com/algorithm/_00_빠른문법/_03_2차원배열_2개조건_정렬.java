package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class _03_2차원배열_2개조건_정렬 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        /* 2차원 배열 */
        int N = in.nextInt();
        String[][] arr = new String[N][2];


        for(int i = 0; i < N; i++) {
            arr[i][0] = in.next();	// 숫자
            arr[i][1] = in.next();	// 명칭
        }

        Arrays.sort(arr,new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.compare(Integer.parseInt(o1[0]), Integer.parseInt(o2[0]));
            }
        });

        for (String[] ar: arr) {
            System.out.println(ar[0]+" "+ar[1]);
        }
    }
}
