package ch4.람다와함수형인터페이스;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerExample {
    /**
     * 요청받은 내용을 소비한다. = 아무런 값도 리턴하지 않고 요청 받은 내용을 처리한다. (리턴타입 void)
     */

    public static void execute(List<String> nameList, Consumer<String> consumer) {
        for (String name : nameList) {
            consumer.accept(name);
        }
    }

    public static Consumer<String> getExpresiion() {
        /* 리턴해주는 것은 '람다식' 자체이다 */
        return (String name) -> System.out.println(name);
    }

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("정수빈");
        nameList.add("김재호호");

        /* 람다 전달 */
        ConsumerExample.execute(nameList, getExpresiion());
    }
}
