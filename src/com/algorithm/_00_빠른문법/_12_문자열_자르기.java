package com.algorithm._00_빠른문법;

public class _12_문자열_자르기 {
    public static void main(String[] args) {
        String phone_number = "0105155";

        String answer = "abcdadfa";
        answer += phone_number.substring(phone_number.length() - 4);
    }
}
