package ch5.스트림API;

import dto.SampleDto;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C08_DataForEach {
    public static void main(String[] args) {
        /**
         * 스트림은 한번 데이터를 소모하면 다시 사용할 수 없어 새로운 스트림을 만들어야한다.
         * 중간 연산자를 사용해서 스트림의 데이터를 변환하거나 필터링 한후 최종 연산자인 collect 메서드를 통해 컬렉션 프레임워크 객체로 변환하는 경우가 많다.
         * 컬렉션 프레임워크는 데이터를 객체 내에 포함하고 있기 때문에 데이터를 읽어들인 이후 필요하다면 다시 읽어낼 수 있다.
         */
        List<SampleDto> sampleDtoList = new ArrayList<>();
        Stream<SampleDto> sampleDtoStream = sampleDtoList.stream(); // 스트림 객체 생성

        // 최종 결과를 List 로 리턴한다.
        List<SampleDto> soredList = sampleDtoList.stream().sorted().collect(Collectors.toList());

        /**
         * Collector 인터페이스
         * java.util.stream 패키지에 포함되어 있으며 자바 8에서 처음 추가되었다.
         *
         * T : 입력항목의 데이터타입, A: 누적값의 데이터타입, R : 리턴항목의 데이터타입
         * 이렇게 입력, 누적, 결과가 있는 연산을 리듀스 연산이라고 부른다. (최종연산에 해당)
         */

        List<Integer> list = sampleDtoList.stream()
                                .map(SampleDto::getIdx)
                                .collect(Collectors.toList());

        /**
         * java8에서는 Collector 인터페이스를 구현한 유틸리티 클래스 Collectors 를 제공한다.
         */

        /* List 인터페이스의 구현 클래스를 선택하려면 Collectors.toCollection 메서드를 사용하자. */
        List<String> set = sampleDtoList.stream()
                                .map(SampleDto::getName)
                                .collect(Collectors.toCollection(LinkedList::new)); // LinkedList 사용하기

        /* List 뿐만 아니라 원하는 형태의 데이터로 스트림 데이터를 전환할 수 있다. */
        String joined = sampleDtoList.stream()
                            .map(SampleDto::getName)
                            .collect(Collectors.joining(", "));

        /* 특정 값의 합을 반환할 수 있다. */
        int total = sampleDtoList.stream()
                        .collect(Collectors.summingInt(SampleDto::getIdx));

        /* Map 객체로 변환할 수 있다. */
        Map<Integer, List<SampleDto>> test1 = sampleDtoList.stream()
                                                    .collect(Collectors.groupingBy(SampleDto::getIdx));

        Map<Integer, Integer> test2 = sampleDtoList.stream()
                                                .collect(Collectors.groupingBy(SampleDto::getIdx, Collectors.summingInt(SampleDto::getIdx)));

        Map<Boolean, List<SampleDto>> test3 = sampleDtoList.stream()
                                                .collect(Collectors.partitioningBy(sampleDto -> sampleDto.getIdx() >= 10));



    }
}
