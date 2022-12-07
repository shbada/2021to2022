package com.intersection;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 6번 예제에서 Converter 추가하기
 */
public class _7_IntersectionType_Converter {
    interface Pair<T> {
        T getFirst();
        T getSecond();
        void setFirst(T first);
        void setSecond(T second);
    }

    /** 추가 */
    // DelegateTo<Pair<T>> 의 Pair<T> 이 타입의 객체의 메서드를 호출했을때 넘어오는걸 넘기는것
    // 이제 여기는 delegate() 하나 남았다.
    interface ForwardingPair<T> extends DelegateTo<Pair<T>>, Pair<T> {
        // Pair의 메서드를 default 메서드로 오버라이딩
        default T getFirst() {
            return delegate().getFirst();
        }

        default T getSecond() {
            return delegate().getSecond();
        }

        default void setFirst(T first) {
            delegate().setFirst(first);
        }

        default void setSecond(T second) {
            delegate().setSecond(second);
        }
    }

    static class Name implements Pair<String> {
        String firstName;
        String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String getFirst() {
            return this.firstName;
        }

        @Override
        public String getSecond() {
            return this.lastName;
        }

        @Override
        public void setFirst(String first) {
            this.firstName = first;
        }

        @Override
        public void setSecond(String second) {
            this.lastName = second;
        }
    }

    interface DelegateTo<T> {
        T delegate();
    }

    private static <T extends DelegateTo<S>, S> void run(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

    interface Convertable<T> extends DelegateTo<Pair<T>> {
        default void convert(Function<T, T> mapper) {
            Pair<T> pair = delegate();
            pair.setFirst(mapper.apply(pair.getFirst()));
            pair.setSecond(mapper.apply(pair.getSecond()));
        }
    }

    public static void main(String[] args) {
        Pair<String> name = new Name("Seohae", "Kim");

        // Pair 는 4개의 메서드가 이미 있어서 intersection으로 사용할 수가 없다.
//        run((DelegateTo)() -> name, o -> {
//
//        });
        // ForwardingPair 를 생성해서 해결하자!
//        run((ForwardingPair<String>)() -> name, o -> {
//            System.out.println(o.delegate());
//            System.out.println(o.getFirst());
//            System.out.println(o.getSecond());
//        });

        // delegate : () -> name
        run((ForwardingPair<String> & Convertable<String>)() -> name, o -> {
            System.out.println(o); // o는 Pair의 기능을 다 가지고있다.
            System.out.println(o.delegate().getFirst());
            System.out.println(o.getFirst());
            System.out.println(o.getSecond());

            print(o);
            o.convert(s -> s.toUpperCase());
            print(o);
            o.convert(s -> s.substring(0, 2));
            print(o);
        });
    }

    private static <T> void print(Pair<T> pair) {
        System.out.println(pair.getFirst() + " " + pair.getSecond());
    }
}
