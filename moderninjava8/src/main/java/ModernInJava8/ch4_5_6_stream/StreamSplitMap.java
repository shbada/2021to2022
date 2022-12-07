package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSplitMap {
    public static void main(String[] args) {

    }

    /**
     * split map
     */
    static void splitMap() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "AAAAAAAA", "AA"),
                new SampleDto(2, "BBBBBBBB", "BB")
        );

        /**
         * 결과타입 : List<String[]>
         * split("") 실행 이후 우리가 기대한 결과 타입은 Stream<String>이다.
         */
        List<String[]> list = sampleDtoList.stream()
                .map(dto -> dto.getName().split(""))
                .distinct()
                .collect(Collectors.toList());

        /**
         * 결과타입 : List<Stream<String>>
         * 이마저도 우리가 기대한 Stream<String> 결과타입이 아니다.
         * 문제 해결을 위해, 먼저 각 단어를 개별 문자열로 이루어진 배열로 만든 다음에 각 배열을 별도의 스트림으로 만들어야한다.
         */
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamofWords = Arrays.stream(arrayOfWords);

        /* 위 Arrays.stream 메서드 호출 추가 */
        List<Stream<String>> list2 = sampleDtoList.stream()
                .map(dto -> dto.getName().split("")) /* 각 단어를 개별 문자열 배열로 변환 */
                .map(Arrays::stream) /* 각 배열을 별도의 스트림으로 생성 */
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 위 이슈 해결: flatMap 사용
     */
    static void userFlatmap() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "AAAAAAAA", "AA"),
                new SampleDto(2, "BBBBBBBB", "BB")
        );

        /* 위 Arrays.stream 메서드 호출 추가 */
        List<String> list3 = sampleDtoList.stream()
                                    .map(dto -> dto.getName().split("")) /* 각 단어를 개별 문자열 배열로 변환 */
                                    .flatMap(Arrays::stream) /* 생성된 스트림을 하나의 스트림으로 평면화 */
                                    .distinct()
                                    .collect(Collectors.toList());
    }
}
