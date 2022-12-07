package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.Arrays;
import java.util.List;

public class StreamNumberic {
    public static void main(String[] args) {

    }

    static void basic() {
        /* 내부반복 */
        List<SampleDto> sampleDtoList = Arrays.asList(
                new SampleDto(1, "A", "AA"),
                new SampleDto(2, "B", "BB")
        );

        /**
         * 문제점. 아래 코드에는 박싱 비용이 숨어있다. Integer->int로 언박싱해야한다.
         */
        int idxSum = sampleDtoList.stream()
                                    .map(SampleDto::getIdx)
                                    .reduce(0, Integer::sum);

        /**
         * 아래처럼 sum 메소드를 호출할 수 있다면, 위 언박싱 비용이 없어진다.
         * 하지만 .map을 호출한 이후의 결과물은 Stream<T>를 생성하기 때문에 sum 메서드를 호출할 수 없다.
         * 이를 해결하기 위해 '기본형 특화 스트림'을 제공한다.
         */
        /*
        int idxSum2 = sampleDtoList.stream()
                                    .map(SampleDto::getIdx)
                                    .sum();
        */

        /**
         * 숫자 스트림으로 매핑하기
         */
        int idxSum3 = sampleDtoList.stream()
                                    .mapToInt(SampleDto::getIdx) /* 결과를 int type */
                                    .sum(); /* 만약 스트림이 비어있다면 0 (기본값)을 반환 */
    }
}
