package com.algorithm._01_그리디_구현;

/**
 * @Date 2021
 * https://programmers.co.kr/learn/courses/30/lessons/12954
 */
import java.util.Scanner;

public class P12969_직사각형_별찍기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = 0; i < b; i++) {

            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }

            System.out.println("");
        }
    }
}