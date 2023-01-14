package ModernInJava8.ch4_5_6_stream;

import java.util.Arrays;
import java.util.List;

public class StreamFilter {
    public static void main(String[] args) {

    }

    /**
     * 중복 필터링
     */
    static void distinct() {
        /* 내부반복 */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);

        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct() /* 중복 필터링 */
                .forEach(System.out::println);
    }
}
