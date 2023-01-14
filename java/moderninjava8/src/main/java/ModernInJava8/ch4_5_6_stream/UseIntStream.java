package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseIntStream {
    public static void main(String[] args) {

    }

    static void intStream() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * IntStream (기본형의 정수값만 만들 수 있는 숫자 스트림)
         */
        IntStream intStream = sampleDtoList.stream()
                                            .mapToInt(SampleDto::getIdx);
        Stream<Integer> integerStream = intStream.boxed(); /* 숫자 스트림을 스트림으로 변환 */

        /**
         * IntStream에서 0이라는 기본값이 나온다면, 혹시나 잘못된 결과를 도출할 수 있다.
         * 스트림에 요소가 없는 상황과 실제 최대값이 0인 경우를 구별해내야한다.
         * 이를 구분하기위해 OptionalInt 스트림을 사용하자.
         */
        OptionalInt maxIdx = sampleDtoList.stream()
                                            .mapToInt(SampleDto::getIdx)
                                            .max();

        /* orElse : 값이 없을 경우 기본 최대값을 명시적으로 설정 */
        int orElseMaxIdx = maxIdx.orElse(1);
    }
}
