package com.intersection;

import java.io.Serializable;
import java.util.function.Function;

public class _2_IntersectionType_적용 {
    public static void main(String[] args) {
//        hello((Function) s -> s);

        // 람다는 익명클래스처럼 새로운 클래스가 정의되어, 일종의 익명클래스다. (InnerClassLambdaMetafactory)
        // intersection : 람다식이 2개를 구현하도록 해준다.
        //      -> (Function 외의 다른 인터페이스를 넣어도 되므로, 타입을 2개 지정할 수 있다.)
        // & 사용해서 and 로 설정할 수 있다.
        // Marker Interface : Serializable(아무것도 없는 인터페이스) : 구현하면 직렬화 가능하다는 의미
        // Function, Serializable 타입을 모두 만족하는 캐스팅 (이 2개를 합쳤을때의 추상 메서드가 1개면 된다. - 람다 캐스팅에 사용 가능)
        hello((Function & Serializable) s -> s);
        hello((Function & Serializable & Cloneable) s -> s);
    }

    /*
        Function : 람다식 구현에 사용할 수 있는 FunctionalInterface (함수형 인터페이스 - 추상메서드 1개)
     */
//    private static void hello(Function<String, String> o) {
//
//    }

//    private static void hello(Serializable o) {
//
//    }

    private static <T extends Function & Serializable & Cloneable> void hello(Serializable o) {

    }
}
