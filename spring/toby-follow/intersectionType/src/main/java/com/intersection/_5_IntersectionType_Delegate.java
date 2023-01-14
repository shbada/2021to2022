package com.intersection;

import java.util.function.Consumer;

public class _5_IntersectionType_Delegate {
    interface DelegateTo<T> {
        T delegate();
    }

    interface Hello extends DelegateTo<String> {
        default void hello() {
            System.out.println("Hello " + delegate()); // delegate() 호출이 가능
        }
    }

    interface UpperCase extends DelegateTo<String> {
        default void upperCase() {
            System.out.println(delegate().toUpperCase()); // delegate() 호출이 가능
        }
    }

    public static void main(String[] args) {
        // "Seohae kim" : delegate 을 구현한 식
        // 람다식에서 delegate()를 호출하면 문구가 호출되겠다. -> hello에서 호출되는 로직
//        run((DelegateTo<String> & Hello)() -> "Seohae kim", o -> {
//            o.hello();
//        });
        // Intersection에 UpperCase 추가
        run((DelegateTo<String> & Hello & UpperCase)() -> "Seohae kim", o -> {
            o.hello();
            o.upperCase();
        });

    }

    private static <T extends DelegateTo<S>, S> void run(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }
}
