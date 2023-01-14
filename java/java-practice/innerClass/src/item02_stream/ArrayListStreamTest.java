package item02_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamTest {
    public static void main(String[] args) {
        List<String> sList = new ArrayList<>();
        sList.add("Tomas");
        sList.add("Edward");
        sList.add("Jack");

        /* Collection 은 stream 메서드를 바로 호출할 수 있다. */
        Stream<String> stream = sList.stream();
        stream.forEach(System.out::println);

        System.out.println("----------");

        /* 정렬하여 출력 */
        sList.stream().sorted().forEach(s -> System.out.print(s + "\t"));

        System.out.println("----------");

        /** map */
        /* 각 단어의 length 를 구해서 출력하자 */
        sList.stream().map(String::length).forEach(s -> System.out.print(s + "\t"));

        System.out.println("----------");

        /** filter */
        sList.stream().filter(s -> s.length() > 4).forEach(System.out::println);
    }
}
