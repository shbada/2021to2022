package com.java.effective.item37;

import java.util.*;

public class Main {
    public static void usingOrdinalArray(List<Plant> garden) {
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

        for (int i = 0 ; i < plantsByLifeCycle.length ; i++) {
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant plant : garden) {
            // ordinal() 인덱싱
            plantsByLifeCycle[plant.lifeCycle.ordinal()].add(plant);
        }

        for (int i = 0 ; i < plantsByLifeCycle.length ; i++) {
            System.out.printf("%s : %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }
    }

    public static void usingEnumMap(List<Plant> garden) {
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }
    }
}
