package com.java.effective.item30;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

public class Identity {
    public static void main(String[] args) {
        String[] strings = {"a", "b", "c"};

        UnaryOperator<String> same = identityFunction();
        Arrays.stream(strings).map(same::apply).forEach(System.out::println);

        Number[] numbers = {1, 2.0, 3L};

        UnaryOperator<Number> sameNumber = identityFunction();
        Arrays.stream(numbers).map(sameNumber::apply).forEach(System.out::println);
    }

    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }
}
