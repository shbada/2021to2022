package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamOperation {
    public static void main(String[] args) {
        operation();
    }

    /**
     * 스트림 연산
     */
    static void operation() {
        /* java8 */
        List<SampleDto> sampleDtoList = new ArrayList<>();

        List<String> list = sampleDtoList.stream()
                /** 중간연산 시작 ----------------------- */
                                        .filter(d -> d.getIdx() < 10) // idx가 10보다 작은 데이터 선택
                                        .sorted(Comparator.comparing(SampleDto::getIdx)) // idx 순서로 정렬
                                        .map(SampleDto::getName) // 이름 추출
                                        .limit(3) // 선착순 3개만 선택
                /** 최종연산 시작 ----------------------- */
                                        .collect(Collectors.toList()); // 리스트로 저장
    }

    /**
     * 스트림 중간연산에 코드 넣기
     */
    static void opretion2() {
        /* java8 */
        List<SampleDto> sampleDtoList = new ArrayList<>();

        List<String> list = sampleDtoList.stream()
                /** 중간연산 시작 ----------------------- */
                .filter(d -> {
                    System.out.println(d.getName());
                    return d.getIdx() < 10; // return 변경
                })
                .sorted(Comparator.comparing(SampleDto::getIdx))
                .map(d -> {
                    System.out.println(d.getName());
                    return d.getName(); // return 변경
                })
                .limit(3)
                /** 최종연산 시작 ----------------------- */
                .collect(Collectors.toList());
    }
}
