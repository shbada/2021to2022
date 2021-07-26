package ch02_스트림;

import dto.Product.Product;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class M02_특정필드로_리스트_추출 {

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

        /* 스트림의 내부반복 - getProductName */
        List<String> productNameList = products.stream()
                                               .filter(product -> {
                                                   System.out.println("filtering: " + product.getProductName());
                                                   return product.getTotalCnt() > 200;
                                               })
                                               .map(product -> {
                                                   System.out.println("mapping: " + product.getProductName());
                                                   return product.getProductName();
                                               })
                                               .limit(3)
                                               .collect(Collectors.toList());

    }
}
