package com.java.effective.item37;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

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

//    public static void usingEnumMap(List<Plant> garden) {
//        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
//
//        for (Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
//            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
//        }
//
//        for (Plant plant : garden) {
//            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
//        }
//    }

    public static void main(String[] args) {
        List<Plant> garden = new ArrayList<>();
        garden.add(new Plant("one", Plant.LifeCycle.ANNUAL));
        garden.add(new Plant("two", Plant.LifeCycle.BIENNIAL));

        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);

        for (Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }

        for (Plant plant : garden) {
            plantsByLifeCycle.get(plant.lifeCycle).add(plant);
        }

        System.out.println("a");
    }

//    public static void main(String[] args) {
//        List<Plant> garden = new ArrayList<>();
//        garden.add(new Plant("one", Plant.LifeCycle.ANNUAL));
//        garden.add(new Plant("two", Plant.LifeCycle.BIENNIAL));
//
//        Map<Plant.LifeCycle, List<Plant>> collect = garden.stream()
//                .collect(groupingBy(p -> p.lifeCycle));
//
//        EnumMap<Plant.LifeCycle, Set<Plant>> collect1 = garden.stream()
//                .collect(groupingBy(p -> p.lifeCycle,
//                        () -> new EnumMap<>(Plant.LifeCycle.class), toSet()));
//
//        System.out.println(collect);
//        System.out.println(collect1);
//    }
}
