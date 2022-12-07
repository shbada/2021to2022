package com.java.effective.item37;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public enum PhaseEnumMap {
    SOLID, LIQUID, GAS;

    public enum Transition {

        MELT(SOLID, LIQUID),
        FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS),
        CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS),
        DEPOSIT(GAS, SOLID);

        private final PhaseEnumMap from;
        private final PhaseEnumMap to;

        Transition(PhaseEnumMap from, PhaseEnumMap to) {
            this.from = from;
            this.to = to;
        }

        /**
         * 이전 상태에서 '이후 상태에서 전이로의 맵'을 대응시키는 맵
         */
        private static final Map<PhaseEnumMap, Map<PhaseEnumMap, Transition>>
                // groupingBy : 전이를 이전 상태를 기준으로 묶는다.
                m = Stream.of(values()).collect(groupingBy(t -> t.from,
                    () -> new EnumMap<>(PhaseEnumMap.class),
                    // toMap : 이후 상태를 전이에 대응시키는 EnumMap 생성한다.
                    toMap(t -> t.to, t -> t,
                            // (x,y) -> y 는 선언만 하고 실제로는 쓰이지 않는다.
                            // -> 단지 EnumMap을 얻으려면 맵 팩터리가 필요하고,
                            //    수집기들은 점층적 팩터리를 제공하기 때문이다.
                            (x, y) -> y, () -> new EnumMap<>(PhaseEnumMap.class))));

        public static Transition from(PhaseEnumMap from, PhaseEnumMap to) {
            return m.get(from).get(to);
        }

        public static void main(String[] args) {
            System.out.println(m);
        }
    }
}
