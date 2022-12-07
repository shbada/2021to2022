package ch5.스트림API;

import java.util.stream.Stream;

public class C03_StreamBuilder {
    public static void main(String[] args) {
        /**
         * 대부분의 개발자들이 가장 처음 스트림을 반영할때, 컬렉션 프레임워크에 포함된 데이터를 처리하는 용도일 것이다.
         * 스트림 객체를 생성하는 시점은 이미 컬렉션 프레임워크를 이용해서 객체 내부의 모든 데이터를 추가한 이후가 대부분이다.
         * 그러므로, 스트림은 데이터를 소모하는 역할만 하고 데이터를 생성하는 역할을 수행하지 않는다.
         * 하지만 스트림에서는 데이터를 직접 생성하기 위한 기능도 제공한다. => 스트림 빌더
         *
         * 스트림 빌더의 사용 이점
         * 1) 스트림 API 자체적으로 스트림 구성 항목을 생성할 수 있다.
         * 2) 스트림 객체를 생성하기 위해 List 등의 컬렉션 프레임워크를 이용해서 임시로 데이터를 만드는 작업을 하지 않아도 된다.
         *
         * Stream.Builder 인터페이스
         * > accept (스트림 빌더에 데이터를 추가하기위한 메서드)
         * > add (스트림 빌더에 데이터를 추가하기위한 메서드; 기존에 추가한 데이터와 현재 추가한 데이터가 포함된 Builder 객체를 리턴)
         * > build (데이터 추가 작업을 종료; build 메서드를 호출하면 스트림 객체가 생성되어 리턴된다.)
         *   build 메서드 호출 이후부터는 add 메서드를 이용해서 데이터를 추가할 수 없다.
         */

        /* accept (void) */
        Stream.Builder<String> builder = Stream.builder();

        builder.accept("1");
        builder.accept("2");
        builder.accept("3");
        builder.accept("4");
        builder.accept("5");
        builder.accept("6");
        builder.accept("7");
        builder.accept("8");

        builder.build().forEach(System.out::println);


        /* add (Stream.Builder<T>) */
        Stream.Builder<String> builder2 = Stream.builder();
        builder2.add("1")
                .add("2")
                .add("3")
                .add("4")
                .add("5")
                .add("6")
                .add("7")
                .add("8")
                .build()
                .forEach(System.out::println);

        /**
         * 스트림 빌더 역시 한번 사용하고 나면 재사용할 수 없다.
         */
    }
}
