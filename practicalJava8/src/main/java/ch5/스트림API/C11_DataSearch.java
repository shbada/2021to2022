package ch5.스트림API;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class C11_DataSearch {
    public static void main(String[] args) {
        /**
         * 스트림 API 에서는 검색 기능을 제공한다.
         * allMatch, anyMatch, noneMatch, fineFirst, findAny
         */

        // Predicate 인터페이스를 구현한 람다 표현식이나 클래스를 전달해야하며, 이를 통해 데이터 비교 작업을 수행한다.
        // 특정 조건에 해당하거나 해당하지 않을 경우 바로 연산 작업을 종료한다.

        List<Integer> numberList = Arrays.asList(1, 3, 5, 7, 9);

        // allMatch (모두 10보다 작나요?)
        boolean answer1 = numberList.stream().allMatch(number -> number < 10);

        // anyMatch (10보다 작은게 1개라도 있나요?)
        boolean answer2 = numberList.stream().anyMatch(number -> number < 10);

        // noneMatch (10보다 작은게 없나요?)
        boolean answer3 = numberList.stream().noneMatch(number -> number < 10);

        /**
         * Match 계열 메서드는 최종 연산자라서 연결해서 비교할 수 없다.
         * 연결 작업은 스트림 API를 이용하지 말고 파라미터로 전달하는 람다 표현식에서 처리할 수 있도록 하는것에 유리하다.
         */

        /**
         * findFirst : 스트림이 가지고 있는 값 중 첫번째를 리턴한다.
         * findAny : 스트림이 가지고 있는 데이터 중 임의의 값을 리턴한다. 없을 경우를 대비해서 Optional 로 감싸서 리턴한다.
         */

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 4보다 작은 값을 필터링하여 그 결과 중 임의의 값 하나를 가져와서 결과를 확인하는 코드
        Optional<Integer> result = list.stream().parallel().filter(num -> num < 4).findAny();

        System.out.println(result.get());


    }
}
