package ch5.스트림API;

import java.util.HashMap;
import java.util.Map;

public class C08_DataCollect {
    public static void main(String[] args) {
        /**
         * forEach(Consumer<? super T> action : Consumer 인터페이스는 함수형 인터페이스로 accept 메서드 1개를 가지고있다. (return void)
         * forEachOrdered(Consumer<? super T> action : 스트림에 포함된 항목들을 정렬해서 처리할때 사용
         */

        // Map 인터페이스의 forEach 메서드와 스트림의 forEach 메서드는 다르다.
        // Map 인터페이스의 forEach
        // 입력 파라미터: BiConsumer (Consumer 인터페이스와 유사하게 함수형 메서드의 리턴 타입이 void 지만 입력받는 파라미터가 2개이다)
        // 리턴타입이 void인 점이 동일하지만 default 메서드이다. (Map 인터페이스를 상속받아 구현한 클래스에 영향을 주지 않기 위함)
        // ex) map.forEach(key, value) -> String.format("key :" + key + "value: " + value)
        // map 은 가변객체이기 때문에 for문 실행 중에 key가 추가되거나 한다면 문제가 발생한다.

        // EntrySet
        Map<String, Object> map = new HashMap<>();
        for (Map.Entry<String, Object> element : map.entrySet()) {
            System.out.println(element.getKey() + ". " + element.getValue());
        }
    }
}
