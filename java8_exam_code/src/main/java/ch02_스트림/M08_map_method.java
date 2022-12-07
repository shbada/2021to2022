package ch02_스트림;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class M08_map_method {
    public static void main(String[] args) {
        /* Map */
        Map<String, Object> testMap2
                = Map.ofEntries(entry("AAA", 10),
                entry("BBB", 20),
                entry("CCC", 30));

        for (Map.Entry<String, Object> entry : testMap2.entrySet()) {
            System.out.println(entry.getKey());
        }

        testMap2.forEach((name, num) -> System.out.println(name + " : " + num));

        /* key 정렬 */
        testMap2.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEachOrdered(System.out::println);

        /* key 가 null 일 경우 */
        System.out.println(testMap2.getOrDefault("AAA", "NULL")); // 10
        System.out.println(testMap2.getOrDefault("DDD", "NULL")); // NULL

        /* 계산 패턴 */
        /*
           1) computeIfAbsent : 제공된 키에 해당하는 값이 없으면, 키를 이용해 새 값을 계산하고 맵에 추가
           2) computeIfPresent : 제공된 키가 존재하면 새 값을 계산하고 맵을 추가
           3) compute : 제공된 키로 새 값을 계산하고 맵에 저장
         */
        /* Map */
        Map<String, Object> testMap3 = new HashMap<>();
        testMap3.put("AAA", 10);

        testMap3.computeIfAbsent("QQQ", num -> 50); // {QQQ=50, AAA=10}
        System.out.println(testMap3);

        /* 삭제 (key-value 매칭) */
        testMap3.remove("QQQ", 40);
        System.out.println(testMap3); // {QQQ=50, AAA=10}

        testMap3.remove("QQQ", 50);
        System.out.println(testMap3); // {AAA=10}

    }
}
