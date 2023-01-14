package com.generic;

import java.util.ArrayList;
import java.util.List;

public class _1_Generics {
    // T : Type Parameter (타입 파라미터)
    static class Hello<T> {
        T t; // 변수로도 사용 가능

        // 메서드 매개변수로 사용 가능
        T method(T value) {
            return null;
        }
    }

    static void print(String value) {
        System.out.println(value);
    }

    public static void main(String[] args) {
        // String : type argument (타입인자)
        new Hello<String>(); // T 에 String

        List<String> list = new ArrayList<>();
//        list.add(1); // 컴파일 에러 - 타입 매칭
        list.add("abc");

        // 타입을 지정하지 않으면 컴파일 오류로 캐치되지 못할 수 있다.
//        List list2 = new ArrayList();
//        String s = (Integer) list2.get(0);
    }
}
