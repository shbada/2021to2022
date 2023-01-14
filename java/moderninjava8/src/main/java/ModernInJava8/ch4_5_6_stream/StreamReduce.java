package ModernInJava8.ch4_5_6_stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduce {
    public static void main(String[] args) {

    }

    /**
     * reduce 연산
     */
    static void useReduce() {
        /* before */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        int sum = 0;

        for (int x : numbers) {
            sum += x;
        }

        /* java 8 */
        /**
         * reduce
         * identity 의미 = 초기값 : 0
         */
        // int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum2 = numbers.stream().reduce(0, Integer::sum);

        /**
         * 만약 초기값을 설정하지 않는다면? Optional 로 받자.
         * 스트림에 아무 요소가 없을 경우 Optional 객체로 감싼 객체를 반환한다
         */
        Optional<Integer> sum3 = numbers.stream().reduce(Integer::sum);

        /**
         * max, min
         */
        int getMax = numbers.stream().reduce(0, Integer::max);
        int getMin = numbers.stream().reduce(0, Integer::min);
    }
}
