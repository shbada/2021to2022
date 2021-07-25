package ch5.스트림API;

import dto.SampleDto;

import java.util.*;
import java.util.stream.Stream;

public class C10_StreamFlattening {
    public static void main(String[] args) {
        /**
         * 스트림의 생성
         * 1) Collection.stream() : 컬렉션 프레임워크의 Collection 인터페이스에 있는 stream 메서드를 호출한다.
         * 2) Arrays.stream(Object[]) : 배열을 스트림으로 변환하는 메서드이다.
         */

        String[] testList = {"aa", "bb", "cc", "dd"};
        Stream<String> sampleDtoStream = Arrays.stream(testList);

        /**
         * 데이터 항목을 이용해서 직접 스트림 객체를 만드는 방법도 있다.
         * Stream 인터페이스에서는 스트림 객체를 생성할 수 있는 of 메서드를 제공한다.
         */

        // of 메서드는 static 메서드이다. 인터페이스 안에 static 메서드가 단독으로 구현되어 있다.
        Stream<SampleDto> stream = Stream.of(new SampleDto(1, "aa", "10"),
                                             new SampleDto(2, "bb", "20"));

    }
}
