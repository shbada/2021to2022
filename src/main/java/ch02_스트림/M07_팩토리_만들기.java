package ch02_스트림;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class M07_팩토리_만들기 {
    public static void main(String[] args) {
        /* List */
        List<String> testList = Arrays.asList("AAA", "BBB", "CCC");
        // testList.add("DDD"); // 고정 리스트로, 요소를 추가/삭제 할 수 없다.

        // 리스트를 인수로 받는 HashSet 생성자를 사용하여 생성할 수 있다.
        Set<String> testSetList = new HashSet<>(Arrays.asList("AAA", "BBB", "CCC"));
        testSetList.add("DDD");


        System.out.println(testSetList);

        /* 변경할 수 없는 리스트 */
        List<String> testLit2 = List.of("AAA", "BBB", "CCC");
        // testLit2.add("DDD"); // 변경할 수 없는 리스트
        System.out.println(testLit2);

        /** 따라서 팩토리 생성은 데이터 처리 형식을 설정하거나, 데이터 변환할 필요가 없을때 사용할 것을때권장한다 */

        /* Set */
        Set<String> testSet = Set.of("AAA", "BBB", "CCC", "DDD");
        System.out.println(testSet);

        /* Map */
        Map<String, Object> testMap = Map.of("AAA", 10, "BBB", 20, "CCC", 30);
        System.out.println(testMap);

        /* Map */
        Map<String, Object> testMap2
                = Map.ofEntries(entry("AAA", 10),
                                entry("BBB", 20),
                                entry("CCC", 30));
        System.out.println(testMap2);
    }
}
