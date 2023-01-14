package com.itvillage.chapter04.chapter0401;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 함수 디스크립터의 Consumer 예제
 * 함수형 인터페이스의 추상 메서드를 설명해놓은 시그니처 함수
 * Java8에서는 java.util.function 패키지로 다양한 새로운 함수형 인터페이스를 지원한다.
 */
public class _6_FunctionalDescriptorConsumer {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 6, 10, 30, 65, 70, 102);
        forEachPrint(numbers, n -> System.out.println(n));
    }

    public static <T> void forEachPrint(List<T> numbers, Consumer<T> c) { // T : int형
        for(T number : numbers)
            c.accept(number);
    }
}
