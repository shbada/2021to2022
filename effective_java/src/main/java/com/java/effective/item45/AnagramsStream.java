package com.java.effective.item45;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class AnagramsStream {
    public static void main(String[] args) throws IOException {
        Path dictionary = Paths.get(args[0]);
        int minGroupSize = Integer.parseInt(args[1]);

        try (Stream<String> words = Files.lines(dictionary)) {
            words.collect(groupingBy(word -> alphabetize(word)))
                    .values().stream()
                    .filter(group -> group.size() >= minGroupSize)
                    .forEach(group -> System.out.println(group.size() + ": " + group));
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);

        "Hello Words".chars().forEach(x -> System.out.println((char) x));
        return new String(a);
    }

//    public static void main(String[] args) throws IOException {
//        Path dictionary = Paths.get(args[0]);
//        int minGroupSize = Integer.parseInt(args[1]);
//
//        try (Stream<String> words = Files.lines(dictionary)) {
//            words.collect(
//                            groupingBy(word -> word.chars().sorted()
//                                    .collect(StringBuilder::new,
//                                            (sb, c) -> sb.append((char) c),
//                                            StringBuilder::append).toString()))
//                    .values().stream()
//                    .filter(group -> group.size() >= minGroupSize)
//                    .map(group -> group.size() + ": " + group)
//                    .forEach(System.out::println);
//        }
//    }
}
