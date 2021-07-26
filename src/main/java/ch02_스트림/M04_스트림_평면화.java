package ch02_스트림;

import dto.Product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class M04_스트림_평면화 {

    public static void main(String[] args) {
	    // sample List
        List<String> wordList = Arrays.asList(
                "AAA",
                "BBB",
                "CCC"
        );

        /* 원하는 결과 : List<String> / 결과 : List<String[]> */
        List<String[]> productNameList = wordList.stream()
                                                .map(word -> word.split(""))
                                                .collect(Collectors.toList());


        // sample List
        String[] wordArray = {"AAA", "BBB", "CCC"};
        Stream<String> wordStream = Arrays.stream(wordArray);

        /* 원하는 결과 : Stream<String> / 결과 : List<Stream<String>> */
        List<Stream<String>> productNameList2 = wordList.stream()
                                                .map(word -> word.split(""))
                                                .map(Arrays::stream)
                                                .collect(Collectors.toList());

        /** flatMap 사용 */
        List<String> productNameList3 = wordList.stream()
                                                        .map(word -> word.split(""))
                                                        .flatMap(Arrays::stream) /* 평면화 리턴 */
                                                        .collect(Collectors.toList());


    }

}
