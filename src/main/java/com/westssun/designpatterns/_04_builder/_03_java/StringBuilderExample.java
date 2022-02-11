package com.westssun.designpatterns._04_builder._03_java;

public class StringBuilderExample {

    public static void main(String[] args) {
        // StringBuffer : synchronized 쓰고, StringBuilder : synchronized 쓴다.
        StringBuilder stringBuilder = new StringBuilder();
        // 빌더를 만들고 문자열을 추가적으로 append 하고, 최종적으로 toString 으로 원하는 문자열을 얻는다.
        String result = stringBuilder
                .append("whiteship")
                .append("keesun")
                .toString();

        System.out.println(result);
    }
}
