package ch02_스트림;

import dto.Product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class M05_컬렉션 {

    public static void main(String[] args) {
        // sample List
        List<Product> products = Arrays.asList(
                new Product(0, "Note_red", 1, 100),
                new Product(1, "Note_blue", 2, 200),
                new Product(2, "Note_green", 3, 300),
                new Product(3, "Note_pink", 4, 400),
                new Product(4, "Note_yellow", 5, 500),
                new Product(5, "Note_black", 6, 600),
                new Product(6, "Note_white", 7, 700),
                new Product(7, "Note_purple", 8, 800)
        );

        /* Collectors 의 정적 메서드 counting */
        long count = products.stream()
                             .collect(Collectors.counting());

        System.out.println("count : " + count);

        /* Stream 메서드 */
        long count2 = products.stream()
                              .count();

        System.out.println("count2 : " + count2);

        /* 문자열 연결 */
        String productName = products.stream()
                                    .map(Product::getProductName)
                                    .collect(Collectors.joining(","));
        System.out.println("productName : " + productName);

        String productName2 = products.stream()
                                    .map(Product::getProductName)
                                    .collect(joining(","));
        System.out.println("productName2 : " + productName2);

    }

}
