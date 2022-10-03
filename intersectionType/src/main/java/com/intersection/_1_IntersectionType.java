package com.intersection;

import java.io.Serializable;
import java.util.function.Function;

public class _1_IntersectionType {
    public static void main(String[] args) {
        hello((String s) -> s);
        hello(s -> s); // String 타입인걸 안다. hello()를 보고 알 수 있다.
        hello(s -> s.toUpperCase());
        hello(String::toUpperCase);  // 메서드 참조

        // 이렇게도 가능하다.
        Function<String, String> f = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        };
        hello(f);

        // 익명클래스
        hello(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        });
    }

    /*
        Function : 람다식 구현에 사용할 수 있는 FunctionalInterface (함수형 인터페이스 - 추상메서드 1개)
     */
    private static void hello(Function<String, String> o) {

    }
}
