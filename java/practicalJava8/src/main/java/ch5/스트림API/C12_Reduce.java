package ch5.스트림API;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class C12_Reduce {
    public static void main(String[] args) {
        /**
         * 최종 연산의 구분
         * 1) 스트림 항목들을 처리하면서 처리 결과를 바로 알 수 있는 최종연산 (forEach 등)
         * 2) 스트림의 데이터를 모두 소모한 후에야 그 결과를 알 수 있는 최종연산 (count, max, min, sum 등)
         */

        /**
         * 위 최종연산 중에서 데이터를 최종적으로 다 확인해서 결괏값을 도출하는 최종연산을 자바의 스트림 API 에서는 리듀스 연산이라고도 부른다.
         */

        int sum = 0;
        int count = 0;

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // list.stream().forEach(value -> {sum += value; count++;}); -> 로컬 변수를 람다 표현식 내부에 사용할 수 없다. (final 또는 final 유사한 상태여야한다.)

        final int sum2 = 0;
        // list.stream().forEach(value -> {sum2 += value; count++;}); -> final 변수는 선언 이후 값을 변경할 수 없다.

        /**
         * IntStream 사용
         */

        // mapToInt 의 결과 스트림: IntStream 에서 제공하는 sum 메서드 사용
        int intStreamSum = list.stream().mapToInt(Integer::intValue).sum();

        /**
         * collect 연산
         * Stream 인터페이스의 collect 메서드 : 스트림의 데이터를 특정한 데이터 형태로 변환하는 역할을 한다.
         */

        // List<String> finalList = stream.collect(Collectors.toList());
        // 스트림 데이터의 총 합곗값을 구할 수 있다.
        // int sum2 = intList.stream().collect(Collectors.summingInt(Integer::intValue));

        /**
         * reduce(T indentity, BinaryOperator<t> accumulator)
         * 첫번째 인수 : 초기값 (합계를 구한다면 0을 사용하겠지만, 다른 데이터를 누적시킬때 사용 가능하다.)
         * 두번째 인수 : BinaryOperator (두개의 인수를 받아서 하나의 값으로 리턴하는 함수형 인터페이스이다.)
         */

        // Integer 의 sum 메서드는 2개의 인잣값을 합쳐서 그 결과를 리턴한다.
        int sum3 = list.stream().reduce(0, Integer::sum); // 메서드 참조

        int sum4 = list.stream().reduce(0, (x, y) -> x+ y); // 람다 표현식

        // 병렬 처리로도 가능
        // stringList.parallelStream().reduce(0, (x, y) -> x + y);

        /**
         * 리듀스 연산 응용
         */
        List<Integer> intList = Arrays.asList(4, 2, 8, 1, 9, 6, 7, 3, 5);

        // 최댓값
        int max = intList.stream().reduce(0, Integer::max);

        // 최솟값
        int min = intList.stream().reduce(0, Integer::min);
    }
}
