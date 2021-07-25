package ModernInJava8.ch4_5_6_stream;

import ModernInJava8.common.SampleDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 기존 Java7 코드와 Java8 코드를 비교해보자.
 */
public class BasicStream {
    public static void main(String[] args) {
        java7Code();
        java8Code();
    }

    /**
     * java7
     */
    static void java7Code() {
        /* java7 */
        List<SampleDto> list = new ArrayList<>();
        List<SampleDto> sampleDtoList = new ArrayList<>();

        for (SampleDto sampleDto : sampleDtoList) {
            if (sampleDto.getIdx() < 10) {
                list.add(sampleDto);
            }
        }

        /* 익명클래스 */
        Collections.sort(list, new Comparator<SampleDto>() {
            @Override
            public int compare(SampleDto o1, SampleDto o2) {
                return Integer.compare(o1.getIdx(), o2.getIdx());
            }
        });

        /* 정렬된 리스트 중 sampleDto의 getName 셋팅 */
        List<String> stringList = new ArrayList<>();
        for (SampleDto sampleDto : list) {
            stringList.add(sampleDto.getName());
        }
    }

    /**
     * java8
     */
    static void java8Code() {
        /* java8 */
        List<SampleDto> sampleDtoList = new ArrayList<>();

        List<String> list = sampleDtoList.stream()
                /** 람다를 인수로 받아, 스트림에서 특정 요소를 제외시킨다. 아래는 idx가 10 이상인 데이터를 선택한다. */
                                        .filter(d -> d.getIdx() < 10) // idx가 10보다 작은 데이터 선택
                                        .sorted(Comparator.comparing(SampleDto::getIdx)) // idx 순서로 정렬
                /** 람다를 이용해서 한 요소를 다른 요소로 변환하거나 정보를 추출한다. */
                                        .map(SampleDto::getName) // 이름 추출
                                        //.limit(3) // 선착순 3개만 선택
                                        .collect(Collectors.toList()); // 리스트로 저장

        /* 병렬 */
        List<String> parallelList = sampleDtoList.parallelStream()
                                                .filter(d -> d.getIdx() < 10) // idx가 10보다 작은 데이터 선택
                                                .sorted(Comparator.comparing(SampleDto::getIdx)) // idx 순서로 정렬
                                                .map(SampleDto::getName) // 이름 추출
                                                .collect(Collectors.toList()); // 리스트로 저장
    }

    /**
     * 스트림은 단 한번만 소비 가능하다.
     */
    static void impossibleNewStream() {
        List<String> title = Arrays.asList("A", "B", "C");
        Stream<String> s = title.stream();

        s.forEach(System.out::println); // A, B, C 출력
        /** java.lang.IllegalStateException:스트림이 이미 소비되었거나 닫힘 에러 발생 */
        s.forEach(System.out::println);
    }
}
