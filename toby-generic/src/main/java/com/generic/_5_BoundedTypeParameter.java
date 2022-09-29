package com.generic;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _5_BoundedTypeParameter<T extends List> { // Bounded Type Parameter
    // extends List & A & B -> 여러개도 가능하다. Multiple Bound
    static <T extends List> void print(T t) {

    }

    static <T extends List & Serializable & Comparator & Closeable> void multiList(T t) {

    }

//    static long countGreaterThan(Integer[] arr, Integer elem) {
//        return Arrays.stream(arr)
//                .filter(i -> i > elem)
//                .count();
//    }

//    static <T> long countGreaterThan(T[] arr, T elem) {
//        return Arrays.stream(arr)
//                // s.compare()...하더라도 s 안에 compare()가 정의되어있는지 모른다.
//                // -> Comparable 을 구현한 파라미터만 올 수 있도록 설정한다.
//                .filter(i -> i > elem) // 여기가 문제다. String을 부등호 비교할 순 없다.
//                .count();
//    }

    // 런타임에 제네릭은 타입 정보를 지우도록 되어있다.
    // extends 라는 제약조건은 런타임시에도 남아있다.
    static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
        return Arrays.stream(arr)
                // s.compare()...하더라도 s 안에 compare()가 정의되어있는지 모른다.
                // -> Comparable 을 구현한 파라미터만 올 수 있도록 설정한다. (Comparable<T> : 제네릭 메서드 존재)
//                .filter(i -> i > elem) // 여기가 문제다. String을 부등호 비교할 순 없다.
                // > 0 : elem이 i 보다 크면 양수가 나온다.
                .filter(i -> i.compareTo(elem) > 0) // 여기가 문제다. String을 부등호 비교할 순 없다.
                .count();
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        String[] strArr = new String[]{"a", "b", "c", "d", "e"};

        countGreaterThan(arr, 4);
        countGreaterThan(strArr, "c");
    }
}
