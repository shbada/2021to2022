package com.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2_LowType {
    public static void main(String[] args) {
        // Low Type
        List list3 = new ArrayList<Integer>();

        List<Integer> ints = Arrays.asList(1, 2, 3);
        List rawInts = ints;

        // JAVA5 이전의 코드일 경우
//        @SuppressWarnings("unchecked")
        List<Integer> ints2 = rawInts; // raw Type List 를 넣으면 warning이 뜬다.
        List<String> strs = rawInts; // Integer List -> String List : 컴파일 에러가 안난다!
        String str = strs.get(0); // String 값을 꺼내와서 String 변수에 저장하려고 할때 런타임 에러가 발생
    }
}
