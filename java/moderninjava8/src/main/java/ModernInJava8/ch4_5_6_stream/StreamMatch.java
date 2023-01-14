package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamMatch {
    public static void main(String[] args) {

    }

    /**
     * anyMatch
     */
    static void anyMatch() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * 프레디케이트가 주어진 스트림에서 적어도 한 요소와 일치하는지 확인
         */
        boolean isChekced = sampleDtoList.stream()
                                         .anyMatch(dto -> dto.getIdx() > 10);
    }

    /**
     * allMatch
     */
    static void allMatch() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * 모든 요소가 주어진 프레디케이트와 일치하는지 확인
         */
        boolean isChekced = sampleDtoList.stream()
                                         .allMatch(dto -> dto.getIdx() > 10);
    }

    /**
     * noneMatch
     */
    static void noneMatch() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * 모든 요소가 프레디케이트와 일치하지않는지 확인
         */
        boolean isChekced = sampleDtoList.stream()
                .noneMatch(dto -> dto.getIdx() > 10);
    }

    /**
     * findFirst
     */
    static void useFindFirst() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        /**
         * 첫번째 값을 반환한다
         */
        Optional<Integer> first = numbers.stream()
                                         .map(n -> n * n)
                                         .filter(n -> n % 3 == 0)
                                         .findFirst();
    }
}
