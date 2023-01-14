package com.java.effective.item55;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("A");
        words.add("B");
        words.add("C");
        String lastWordInLexicon = maxOptional(words).orElse("단어 없음");
        // String lastWordInLexicon = maxOptional(words).orElseThrow(RuntimeException::new);
        // String lastWordInLexicon = maxOptional(words).get();
    }

    public static <E extends Comparable<E>> E max(Collection<E> c) {
        /* 빈 컬렉션일 경우 에러를 던진다. */
        if (c.isEmpty()) {
            throw new IllegalArgumentException("빈 컬렉션");
        }

        E result = null;

        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public static <E extends Comparable<E>> Optional<E> maxOptional(Collection<E> c) {
        /* 빈 컬렉션일 경우 빈 Optional 을 반환한다. */
        if (c.isEmpty()) {
            return Optional.empty();
        }

        E result = null;

        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        /* 값이 든 Optional 을 반환한다. */
        return Optional.of(result);

        /* Optional 을 사용하는 경우에는 null을 절대 반환하지 말자.
           null 을 허용하는 Optional 을 반환하자. */
        // return Optional.ofNullable(result);
    }

    public static <E extends Comparable<E>> Optional<E> maxOptionalStream(Collection<E> c){
        return c.stream().max(Comparator.naturalOrder());
    }
}
