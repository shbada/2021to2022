package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMapping {
    public static void main(String[] args) {

    }

    /**
     * Map
     */
    static void useMap() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * 1) Map
         * 함수를 인수로 받는 메서드
         * 인수로 제공된 함수는 각 요소에 적용되며 함수를 적용한 결과가 새로운 요소로 매핑된다.
         * (map은 '고친다'라는 의미 보다는 '새로운 버전을 만든다' 라는 개념에 가까우므로 매핑이라는 단어를 사용한다)
         */
        List<Integer> list = sampleDtoList.stream()
                                            .map(SampleDto::getIdx) /* idx만의 새로운 버전 생성 */
                                            .collect(Collectors.toList());

        /**
         * idx(Integer -> Long)
         * map 메서드의 연결
         */
        List<Long> list2 = sampleDtoList.stream()
                                            .map(SampleDto::getIdx) /* idx만의 새로운 버전 생성 */
                                            .map(Integer::longValue)
                                            .collect(Collectors.toList());
    }
}
