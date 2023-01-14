package com.intersection;

import java.util.function.Consumer;
import java.util.function.Function;

public class _3_IntersectionType_Callback {
    interface Hello {
        // default method
        // 이 인터페이스에 정의한 또다른 일반 메서드, static 메서드 등은 호출이 가능하다.
        // 제한은 있지만 기능 구현이 가능하다.
        default void hello() {
            System.out.println("hello");
        }
    }

    interface Hi {
        default void hi() {
            System.out.println("hi");
        }
    }

    interface Printer {
        default void print(String str) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        // 구현해야할 메서드가 총 합 개수가 1개여야한다.
        hello((Function & Hello & Hi) s -> s);

        // intersection 타입들을 consumer 에서 구현하고 있을 수 있구나 라고 추론 가능
        // s는 사실 의미가 없고, 타입 선언을 위한거라고 생각하자.
        // Printer 도 추가 할 수 있다.
        run((Function & Hello & Hi & Printer) s -> s, o -> {
            // return 값 없는 body
            o.hello();
            o.hi();
            o.print("Lambda");
        });
    }

    /*
       Consumer : 리턴은 없고 파라미터 1개만 있다.
       Supplier : 파라미터가 없고 리턴값 1개 있다.
       Function : 파라미터 있고, 리턴값도 있다.
       BiFunction : 2개를 보내고, 리턴값 1개 있다.
     */
    private static <T extends Function> void run(T t, Consumer<T> consumer) {
        // function 을 구현한 람다를 받아서 consumer 에게 보낸다.
        consumer.accept(t);
    }

    /*
       받는 쪽의 <T extends Function & Hello & Hi> 를 다 나열해야한다.
       -> 불편하다.
       -> Callback 방식으로 만들어보자. -> run()
     */
    private static <T extends Function & Hello & Hi> void hello(T t) {
        t.hello(); // default 기능이 호출이 된다.
        t.hi(); // default 기능이 호출이 된다.
        t.apply("a");
    }

}
