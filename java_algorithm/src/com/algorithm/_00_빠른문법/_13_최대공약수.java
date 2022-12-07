package com.algorithm._00_빠른문법;

public class _13_최대공약수 {
    static int gcd (int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
