package com.intersection;

import java.util.function.Consumer;
import java.util.function.Function;

public class _4_IntersectionType_Delegate {
    interface Hello extends Function { // Function 을 상속했다고 보자.
        default void hello() {
            System.out.println("hello");
        }
    }

    interface Hi extends Function { // Function 을 상속했다고 보자.
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
        hello((Function & Hello & Hi) s -> s);

        /*
           Hello, Hi 에 Function 을 extends 추가해보자. (Function 을 상속했다고 보자.)
           -> 아무 문제가 없다.
           Hello, Hi에 메서드가 각 1개씩 추가됬지만(apply()) Function에 있는 함수와 동일하므로,
           최종적으로는 인터페이스 메서드 1개짜리로 적용되어 가능하다.

           하나의 정보를 가지고 활용하는 각각의 인터페이스에 선언해서 종류별로 사용가능하다.
         */
        run((Function & Hello & Hi & Printer) s -> s, o -> {
            // return 값 없는 body
            o.hello();
            o.hi();
            o.print("Lambda");
        });
    }

    private static <T extends Function> void run(T t, Consumer<T> consumer) {
        // function 을 구현한 람다를 받아서 consumer 에게 보낸다.
        consumer.accept(t);
    }

    private static <T extends Function & Hello & Hi> void hello(T t) {
        t.hello(); // default 기능이 호출이 된다.
        t.hi(); // default 기능이 호출이 된다.
        t.apply("a");
    }

}
