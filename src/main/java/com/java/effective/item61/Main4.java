package com.java.effective.item61;

public class Main4 {
    public static void main(String[] args) {
        Long sum = 0L;

        for (long i = 0 ; i <= Integer.MAX_VALUE ; i++) {
            sum += i;
        }

        System.out.println(sum);
    }
}
