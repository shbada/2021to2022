package ch5.스트림API;

import dto.SampleDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class C07_DataMapping {
    public static void main(String[] args) {
        // 데이터 매핑 map
        /**
         * 스트림 인터페이스
         * map(Function<? super T,? extends R> mapper)
         * mapToDouble(ToDoubleFunction<? super T> mapper)
         * mapToInt(ToIntFunction<? super T> mapper)
         * mapToLong(ToLongFunction<? super T> mapper)
         */

        // Function 인터페이스 : 함수형 메서드 apply 를 제공하며 입력 파라미터로 전달받은 객체를 변환해서 다른 타입의 객체로 리턴한다.

        List<SampleDto> sampleDtoList = new ArrayList<>();
        Stream<SampleDto> sampleDtoStream = sampleDtoList.stream(); // 스트림 객체 생성

        // Stream<SampleDto> -> Stream<String> 으로 변환
        Stream<String> sampleDtoStream2 = sampleDtoList.stream().map((SampleDto t) -> t.toString());

    }
}
