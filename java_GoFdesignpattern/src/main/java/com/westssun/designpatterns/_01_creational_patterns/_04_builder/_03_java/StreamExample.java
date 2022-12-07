package com.westssun.designpatterns._01_creational_patterns._04_builder._03_java;

import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {
        Stream.Builder<String> stringStreamBuilder = Stream.builder();
        Stream<String> names = stringStreamBuilder
                .add("keesun")
                .add("whiteship").
                build();


        // 위 코드는 아래처럼 안된다. (Object type으로 받으므로 String 으로 받아야한다.)
//        Stream<String> names2 = Stream.builder()
//                    .add("keesun")
//                    .add("whiteship").
//                    build();
        // stream builder
        // stream 을 만들때 문자열을 추가적으로 add 하고, 최종적으로 build() 로 얻는다.
        Stream<String> names2 = Stream.<String>builder()
                .add("keesun")
                .add("whiteship").
                build();

        names.forEach(System.out::println);
    }
}
