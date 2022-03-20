package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _11_문자열_치환 {
    public static void main(String[] args) {
        String phone_number = "0105155";

        StringBuilder sb = new StringBuilder();
        sb.append(phone_number);

        for (int i = 0; i < phone_number.length() - 4; i++) {
            sb.replace(i, i + 1, "*");
        }

        String answer = sb.toString();
    }
}
