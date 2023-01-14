package com.java.effective.item32;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        dangerous(list);
    }

    static void dangerous(List<String>... stringLists) {
        List<Integer> intList = List.of(2);
        Object[] objects = stringLists;
        objects[0] = intList; // 힙 오염
        /* stringList 는 Integer 타입으로 변경됨 */
        String s = stringLists[0].get(0); // ClassCastException 발생
    }
}
