package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSlice {
    public static void main(String[] args) {

    }

    /**
     * filter
     */
    static void useFilter() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * 1) filter
         * filter 메서드를 사용하면 전체 스트림을 반복하면서 각 요소에 프레디케이틑 적용하게된다.
         * 만약 idx가 정렬된 리스트일 경우, idx가 10보다 큰 경우부터는 반복 작업을 중단할 수 있다.
         * 이런 경우에는 useTakeWile을 사용해보자. 아래 예제를 보자.
         */
        List<SampleDto> list = sampleDtoList.stream()
                                            .filter(dto -> dto.getIdx() < 10)
                                            .collect(Collectors.toList());

    }

    /**
     * takeWhile
     */
    static void useTakeWile() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * filter 메서드와는 다르게, 조건문을 만족하지 않은 경우 반복 작업을 중단한다.
         */
        List<SampleDto> list = sampleDtoList.stream()
                                            .takeWhile(dto -> dto.getIdx() < 10)
                                            .collect(Collectors.toList());
    }

    /**
     * dropWhile
     */
    static void useDropWhile() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * takeWhile과 정반대의 작업 수행
         * 프레디케이트가 처음으로 거짓이 되는 지점까지 발견된 요소를 제거한다.
         * -> idx가 10보다 큰 요소를 탐색한다.
         */
        List<SampleDto> list = sampleDtoList.stream()
                                            .dropWhile(dto -> dto.getIdx() < 10)
                                            .collect(Collectors.toList());
    }

    /**
     * limit
     */
    static void useLimit() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * limit
         */
        List<SampleDto> list = sampleDtoList.stream()
                                            .filter(dto -> dto.getIdx() < 10)
                                            .limit(3) /* 최대 요소 3개 반환 */
                                            .collect(Collectors.toList());
    }

    /**
     * skip
     */
    static void useSkip() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * skip
         */
        List<SampleDto> list = sampleDtoList.stream()
                .filter(dto -> dto.getIdx() < 10)
                .skip(2) /* 처음 두개의 idx를 건너뛴 후 나머지 idx를 반환한다 */
                .collect(Collectors.toList());
    }
}
