package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 외부반복/내부반복
 외부반복 : 사용자가 직접 요소를 반복 (for-each 등)
 내부반복 : 스트림 라이브러리 -반복을 알아서 처리하고 결과 스트림값을 어딘가에 저장해준다
 */
public class StreamIteration {
    public static void main(String[] args) {
        forEachCode();
    }

    /**
     * 내부반복/외부반복 예제
     */
    static void forEachCode() {
        /* 내부반복 */
        List<String> names = new ArrayList<>();
        List<SampleDto> sampleDtoList = new ArrayList<>();

        for (SampleDto sampleDto : sampleDtoList) {
            names.add(sampleDto.getName());
        }

        /* 외부반복 */
        List<String> streamNames =  sampleDtoList.stream()
                                        .map(SampleDto::getName)
                                        .collect(Collectors.toList());
    }
}
