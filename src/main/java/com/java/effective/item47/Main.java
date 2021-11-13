package com.java.effective.item47;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
