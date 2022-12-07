package com.java.effective.item47;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SubLists {
    public static <E> Stream<List<E>> of(List<E> list) {
        // Stream.concat : 반환되는 스트림에 빈 리스트를 추가
        return Stream.concat(Stream.of(Collections.emptyList()),
                // flatMap : 모든 프리픽스의 모든 서픽스로 구성된 하나의 스트림을 만든다.
                prefixes(list).flatMap(SubLists::suffixes));
    }

    /**
     for (int start = 0; start < src.size(); start++) {
        for (int end = start + 1; end <= src.size(); end++ {
            System.out.println(src.subList(start, end));
        }
     }

     * @param list
     * @param <E>
     * @return
     */
    private static <E> Stream<List<E>> prefixes(List<E> list) {
        // rangeClosed 가 반환하는 연속된 정수값들을 매핑
        return IntStream.rangeClosed(1, list.size())
                .mapToObj(end -> list.subList(0, end));
    }

    private static <E> Stream<List<E>> suffixes(List<E> list) {
        // range 가 반환하는 연속된 정수값들을 매핑
        return IntStream.range(0, list.size())
                .mapToObj(start -> list.subList(start, list.size()));
    }

}
