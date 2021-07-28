package ch02_스트림;

import dto.Product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class M06_무한_스트림 {
    public static void main(String[] args) {
        /* iterate() : 계산된 결과를 가져와서 이어서 수행 */
        // seed : 시작값
        Stream<Integer> evenStream = Stream.iterate(1, n -> n * 2); // 1, 2, 4..

        /* generate() : 이전에 수행된 결과에 관계없이 수행 */
        Stream<Double> randomStream = Stream.generate(Math::random);
    }
}
