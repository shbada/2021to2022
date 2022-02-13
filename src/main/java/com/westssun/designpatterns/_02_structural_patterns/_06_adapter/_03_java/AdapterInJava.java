package com.westssun.designpatterns._02_structural_patterns._06_adapter._03_java;

import java.io.*;
import java.util.*;

public class AdapterInJava {

    public static void main(String[] args) {
        // collections
        /** 전달은 배열로, 받는것은 리스트 */
        List<String> strings = Arrays.asList("a", "b", "c");
        /** 전달은 collection, 받는건 Enumeration */
        // strings : adaptee
        // enumeration() : Adapter
        // Enumeration Type : target Interface
        Enumeration<String> enumeration = Collections.enumeration(strings);
        ArrayList<String> list = Collections.list(enumeration);

        // io
        // "input.txt" : adaptee
        // FileInputStream 생성자 : Adapter
        // InputStream : target Interface
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
