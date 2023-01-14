package com.java.effective.item30;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> s1 = Set.of("a", "b", "c");
        Set<String> s2 = Set.of("d", "e", "f");
        //Set<Integer> s2 = Set.of(1, 2, 3);

        Set<String> union = unionNext(s1, s2);

        // Collections
        System.out.println(union);
    }

    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);

        return result;
    }

    public static <E> Set<E> unionNext(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);

        return result;
    }
}
