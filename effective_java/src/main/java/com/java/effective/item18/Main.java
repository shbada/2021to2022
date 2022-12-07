package com.java.effective.item18;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("A", "B", "C"));

        /* 기댓값 : 3, 결과값 : 6 */
        System.out.println(s.getAddCount());

        /**
         * HashSet 의 addAll 메서드가 add 메서드를 사용하여 구현한다.
         * 이런 내부 구현 방식은 hashSet 문서에는 쓰여있지 않다.
         * InstrumentedHashSet 의 addAll 은 addCount 에 3을 더한후, HashSet의 addAll 을 호출했다.
         * HashSet 의 addAll 은 각 원소를 add 메서드를 호출해 추가하는데, 이때 불리는 add 는 InstrumentedHashSet 에서 재정의한 add 메서드다.
         * 따라서 addCount 에 값이 중복으로 더해져, 최종값이 6으로 늘어난 것이다. addAll 메서드로 추가된 원소 하나당 2씩 늘었다.
         */

//        Set<Instant> times = new InstrumentedSet<Instant>(new TreeSet<Instant>(cmp));
//        Set<E> s = new InstrumentedSet<>(new HashSet<>(INIT_CAPACITY))
    }
}
