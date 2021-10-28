package com.java.effective.item28;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Object[] objectArray = new Long[1];
        objectArray[0] = "sss"; // 런타임시에 에러가 발생

        // 컴파일시 바로 알 수 있다.
        // List<Object> ol = new ArrayList<Long>();
        // ol.add("sss");

//        List<String>[] stringLists = new List<String>[1]; // 만약 허용이 된다면?
//        List<Integer> intList = List.of(31);
//        Object[] objects = stringLists; // List<String> 타입의 배열을 Object 배열에 할당
//        objects[0] = intList; // List<Integer> 인스턴스를 Object 배열의 첫 원소로 저장
//        String s = stringLists[0].get(0);

    }
}
