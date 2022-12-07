package ch5.스트림API;

import dto.SampleDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class C06_DataSort {
    public static void main(String[] args) {
        // sorted(), sorted(Comparator<? super T> comparator)
        List<SampleDto> sampleDtoList = new ArrayList<>();
        Stream<SampleDto> sampleDtoStream = sampleDtoList.stream(); // 스트림 객체 생성

        sampleDtoStream.sorted().forEach(System.out::println);

        /**
         * sorted() 에러 발생 -> why? Comparable 인터페이스 구현이 없기 때문에
         * 객체를 정렬하기 위해서는 값의 크고 작음을 판단할 수 있어야한다.
         * 스트림에서 데이터를 처리할때 Comparable 객체로 형 변환을 시도하였고, SampleDto 파일에는 Comparable 인터페이스를 구현하지 않아서 런타임에러가 발생했다.
         * 문자열 처리를 위한 String, 기본 데이터 타입인 int, long, double 등은 각각의 클래스에 Comparable 인터페이스가 구현되어있어서 정렬이 가능한 것이다.
         *
         * 파라미터로 받을 수 있는 sorted(Comparator<? super T> comparator) 도 있다.
         */

        // sorted(Comparator<? super T> comparator) 을 사용하는 경우
        // 1) Comparable 인터페이스를 구현하지 않은 객체를 정렬할때
        // 2) 역순으로 정렬하고 싶을때
        // 3) 정렬하고자 하는 객체의 키 값을 다르게 하고 싶을때

        /* 반드시 구현해야하는 메소드: compare(T o1, T o2)
         * 1) 첫번째 파라미터가 큰 경우 : 음수 리턴
         * 2) 두번재 파라미터가 큰 경우 : 0 혹은 양수 리
         * */

        // Comparator.reverseOrder()을 사용하려면 Comparable 인터페이스가 구현되어있어야 한다.
        sampleDtoList.stream().sorted(Comparator.comparing(SampleDto::getIdx)).forEach(System.out::println);
        // SampleDto::getIdx -> A.getIdx().compareTo(B.getIdx)
    }
}
