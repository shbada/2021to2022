package ModernInJava8.ch4_5_6_stream;

import java.util.stream.Stream;

public class newStream {
    public static void main(String[] args) {

    }

    static void newStream() {
        /**
         * 스트림 생성
         */
        Stream<String> stream = Stream.of("A", "b", "C", "d");

        stream.map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
