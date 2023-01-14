package com.westssun.designpatterns._02_structural_patterns._09_decorator._03_java;

import java.io.*;
import java.util.*;

public class AdapterInJava2 {

    public static void main(String[] args) {
        // collections
        List<String> strings = Arrays.asList("a", "b", "c");

        Enumeration<String> enumeration = Collections.enumeration(strings);
        ArrayList<String> list = Collections.list(enumeration);

        // 데코레이터 패턴 사용
        // 감싸면서 기능을 추가할 수 있다.
        try (InputStream is = new FileInputStream("input.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr)) {

            while(reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
