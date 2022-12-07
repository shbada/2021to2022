package ch4.람다와함수형인터페이스;

import java.util.function.Predicate;

public class PredicateExample {
    /**
     * 리턴 타입이 반드시 특정 클래스여야한다. (boolean 타입)
     * 제네릭 타입으로 선언된 객체를 파라미터로 받아서 처리한 후 참/거짓 중 하나를 리턴한다.
     */
    public static boolean isValid (String name, Predicate<String> predicate) {
        return predicate.test(name);
    }

    public static void main(String[] args) {
        PredicateExample.isValid("", (String name) -> !name.isEmpty());
    }

}
