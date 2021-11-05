package com.java.effective.item42;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("aa", "bbb", "ccc", "dddd");

        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        Collections.sort(list, (s1, s2) -> Integer.compare(s1.length(), s2.length()));

        Collections.sort(list, Comparator.comparingInt(String::length));
        list.sort(Comparator.comparingInt(String::length));
    }
}
