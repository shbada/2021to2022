package ch5.스트림API;

import java.util.Arrays;
import java.util.List;

public class C01_StreamBasic {
    public static void main(String[] args) {
        /**
         자바 8에서 새롭게 제안된 스트림 API
         목적 : 람다 표현식과 메서드 참조 등의 기능과 결합해서 매우 복잡하고 어려운 데이터 처리 작업을 쉽게 조회하고 필터링하고 변환하고 처리할 수 있도록함
         */

        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};
        List numberList = Arrays.asList(integers);

        for (int i = 0; i < numberList.size(); i++) {
            System.out.println(numberList.get(i));
        }

        /* 스트림 내부에서 개발자가 정의한 코드가 반복적으로 실행된다 */
        numberList.forEach(System.out::println);

        /**
         * 스트림에서 가장 기본이 되는 인터페이스 : BaseStream
         * 제네릭 타입으로 <T, S extends BaseStream<T,S>> 가 인터페이스에 추가적으로 정의되어있다.
         * 1) T: 스트림에서 처리할 데이터의 타입
         * 2) S: BaseStream을 구현한 스트림 구현체를 의미한다. 이 구현체에는 스트림을 자동으로 종료하기위한 AutoCloseable 인터페이스도 구현되어있어야한다.
         *       S 타입으로 지정한 타입은 AutoCloseable 인터페이스의 close 메서드를 반드시 구현해야한다. (스트림을 자동으로 종료 가능하게 하기 위해서)
         */

        // BaseStream 인터페이스는 스트림 API의 최상위에 존재
        // 스트림 객체를 병렬 혹은 순차 방식으로 생성하고 최종적으로 종료하기 위한 명세를 제공한다.
        // BaseStream 을 직접 사용할 일은 없고, 인터페이스를 상속한 Stream 인터페이스를 주로 사용한다.
    }
}
