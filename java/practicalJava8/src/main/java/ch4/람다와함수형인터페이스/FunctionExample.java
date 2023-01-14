package ch4.람다와함수형인터페이스;

import java.util.function.Function;

public class FunctionExample {
    /**
     * 생성할때 두 개의 제네릭 타입을 정의해야 하며 각각의 이름은 T와 R이다.
     * 해당 인터페이스의 함수형 메서드는 T를 인수로 받아서 R을 리턴하는 apply 메서드를 가지고있다.
     * 특정한 클래스를 파라미터로 받아서 처리한 후 리턴하는 형태이다.
     * 리턴값이 반드시 필요할 경우 사용한다
     */

    public static int execution (String context, Function<String, Integer> function) {
        return function.apply(context);
    }

    public static void main(String[] args) {
        /* 인수 T : context, 리턴 R : context.length() */
        FunctionExample.execution("Hello", (String context) -> context.length());
    }
}
