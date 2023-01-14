package ModernInJava8.ch4_5_6_stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteStream {
    public static void main(String[] args) {

    }

    /**
     * iterate
     */
    static void useIterate() {
        /**
         * 무한 스트림 생성
         * 종료시점이 명시되어야한다. -> takeWhile 사용
         */
        IntStream.iterate(0, n -> n + 2)
                .takeWhile(n -> n < 100)
                .forEach(System.out::println);
    }

    /**
     * generate
     */
    static void userGeneriate() {
        /**
         * 무한 스트림 생성
         * iterate와 다른점 : 생산된 각 값을 연속적으로 계산하지 않는다.
         * Supplier<T>를 인수로 받아서 새로운 값을 생성한다.
         * 종료시점 명시 -> limit
         */
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
