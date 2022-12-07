package ch5.스트림API;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class C02_FirstStreamExample {
    public static void main(String[] args) {
        List<String> firstList = new ArrayList<>();
        firstList.add("1");
        firstList.add("2");
        firstList.add("3");
        firstList.add("4");
        firstList.add("5");
        firstList.add("6");
        firstList.add("7");

        System.out.println(firstList + ";");

        Stream<String> firstStream = firstList.stream();
        System.out.println(firstStream.count() + "개"); // 아래 주석 읽기 -> 스트림을 소모하는 순

        /* 스트림에서 상위 5개 데이터로 제한 -> 새로운 스트림 생성 */
        /**
         * 에러 발생 - stream has aleady been operated upon or closed
         * 현재 호추한 스트림의 상태가 정상적이지 않다는 뜻으로 "스트림은 이미 처리되었거나 종료되었음"을 알림
         * -> 스트림은 한번 사용하고 나면 다시 사용할 수 없다
         * -> Stream 인터페이스의 메서드 중 void 를 리턴하는 메소드를 호출하면 전체 스트림 데이터를 처리하기 때문에
         *    데이터를 모두 소모하고 종료된다.
         *
         * + 어떤 시점에 스트림을 소모한 것일까?
         * 스트림 데이터의 크기 조회 로직 (stream.count()) -> 스트림의 데이터를 다 흘려봐야만 알 수 있어서, 총 건수를 알기위해 스트림을 소모하였다.
         */
        Stream<String> limitedStream = firstStream.limit(5);
        limitedStream.forEach(System.out::println);

        /**
         * 에러 발생을 해결하기 위해 코드를 수정해보자.
         */

        System.out.println(firstList.stream().count()); // stream1
        firstList.stream().limit(5).forEach(System.out::println); // stream2
    }
}
