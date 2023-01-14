package com.java.effective.item81;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Intern {
    /* 동시성 정규화 맵 */
    private static final ConcurrentMap<String, String> map =
            new ConcurrentHashMap<>();

    public static String intern(String s) {
        // ConcurrentMap는 get과 같은 검색 기능이 최적화되어있어서, get을 먼저 호출하는게 낫다.
        String previousValue = map.putIfAbsent(s, s);
        return previousValue == null ? s : previousValue;
    }

    public static String intern2(String s) {
        String result = map.get(s);
        if (result == null) {
            result = map.putIfAbsent(s, s);
            if (result == null)
                result = s;
        }
        return result;
    }
}