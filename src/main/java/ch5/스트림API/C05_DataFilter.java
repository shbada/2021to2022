package ch5.스트림API;

import dto.SampleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class C05_DataFilter {
    public static void main(String[] args) {
        // 스트림에 포함된 객체의 속성값에 기반해서 데이터를 추출하는 것
        List<SampleDto> sampleDtoList = new ArrayList<>();
        Stream<SampleDto> sampleDtoStream = sampleDtoList.stream(); // 스트림 객체 생성

        // 필터 조건을 정의
        // Stream<t> filter(Predicate<? super T> predicate;
        /** filter: 현재 스트림에서 주어진 Predicate와 일치하는 항목으로 구성된 스트림을 리턴한다. */
        sampleDtoStream.filter(new Predicate<SampleDto>() { // 대상 필터링 -> 새로운 객체로 반환
            @Override
            public boolean test(SampleDto sampleDto) {
                return "1".equals(sampleDto.getIdx());
            }
        }).forEach(System.out::println); // 필터링된 데이터를 처리

        /* 람다표현식으로 정의 */
        sampleDtoStream.filter((SampleDto sampleDto) -> "1".equals(sampleDto.getIdx())).forEach(System.out::println);

        /**
         * distinct 메서드
         * 1) 성능 저하
         * -> 데이터 중복을 제거하기 위해 여러 스레드에 분산해놓은 데이터를 동기화해서 비교해야하기 때문에 성능이 저하된다.
         * -> 병렬 스트림보다는 순차 스트림에 이용해야 빠르다.
         * -> 전체 데이터를 비교함으로써 내부적으로 버퍼를 많이 사용하여 메모리 효율, CPU 사용량에 영향을 줄 수 있다.
         *
         * 2) 중복 제거가 안되는 경우도 있다.
         * -> 스트림 항목의 중복 여부를 확인하기 위해 equals 메서드가 내부적으로 호출된다.
         * -> String, Object 같은 기본 데이터 타입들은 equals 메서드만으로도 판단이 가능하지만, 그 외에서는 신뢰성이 떨어진다.
         */
        sampleDtoList.stream().distinct().forEach(System.out::println);

        // 문제 발생) 중복 제거가 되지 않았다.
        // 데이터가 모두 같아도, SampleDto의 하나의 객체마다 서로 다른 객체로 이닛ㄱ하기 때문이다.
        // 만약 비교를 하려면, equals 메서드를 오버라이드해서 구현해야 한다.
    }
}
