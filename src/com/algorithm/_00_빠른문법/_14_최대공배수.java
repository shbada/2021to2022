package com.algorithm._00_빠른문법;

public class _14_최대공배수 {
    static int gcd (int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

    static int lcm(int a, int b) {
        return a * b / gcd(a,b);
    }
}
