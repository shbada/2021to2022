package ch02_스트림;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class M09_로깅 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 5, 6);

        numbers.stream()
                .map(x -> x + 3)
                .filter(x -> x % 2 == 0)
                .limit(3)
                .forEach(System.out::println); /* 디버깅 x 결과 출력 */

        /* 로깅 peek */
        /**
         * peek : 스트림 연산
         * 스트림의 각 요소를 소비하는 것처럼 동작을 실행한다.
         * forEach 처럼 실제로 스트림의 요소를 소비하지 않는다.
         * peek 은 자신이 확인한 요소를 파이프라인의 다음 연산으로 그대로 전달한다.
         * peek 은 스트림 파이프라인의 각 동작 전후의 중간값을 출력한다.
         */
        List<Integer> result = numbers.stream()
                .peek(x -> System.out.println("from stream: " + x)) // 처음 소비한 요소를 출력
                .map(x -> x + 3)
                .peek(x -> System.out.println("after map: " + x)) // map 동작 실행 결과를 출력
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x)) // filter 동작 실행 결과를 출력
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x)) // limit 동작 실행 결과를 출력
                .collect(Collectors.toList());

    }
}
