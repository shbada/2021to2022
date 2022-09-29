package com.generic;

import java.util.Arrays;
import java.util.List;

public class _7_WildCards {
    static <T> void method1(List<T> t) {

    }

    // ? 사용 : wildcards (이 타입을 모른다. 알 필요도 없다.)
    static void method2(List<?> t) {

    }

    static <T extends Comparable> void method3(List<T> t) {

    }

    static void method4(List<? extends Comparable> t) {

    }

    /*
     * printList1 vs printList2
     */
    static void printList1(List<Object> list) {
        // s.toString()에만 관심이 있다.
        // Object 이 타입은 알 필요가 없다.
        list.forEach(s -> System.out.println(s));
        System.out.println(list.size());
    }

    static void printList2(List<?> list) {
        // 이렇게도 가능하다.
        list.forEach(s -> System.out.println(s));
        System.out.println(list.size());
    }

    static void printList3(List<?> list) {
        // compareTo() 불가
//        list.forEach(s -> s.compareTo());
    }

    static void printList4(List<? extends Comparable> list) {
        // compareTo() 가능
        list.forEach(s -> s.compareTo(2));
    }

    public static void main(String[] args) {
        // T 사용 : 선언 시점에 이 타입을 모르지만, 이 타입이 정해지면 이 타입을 알고 사용하겠다.
//        List<T> list1;
        // ? 사용 : wildcards (이 타입을 모른다. 알 필요도 없다.)
        // Object 기능만 쓰거나, 아님 여기에 어떤 타입이 오던 상관이 없다.
        // 이 제네릭 타입을 받는 List의 가지고있는 메서드만 사용한다는 의미다. (size, clear 등)
        // 타입과 연관된 add 이런건 안쓴다는 의미
        List<?> list2;

        // Object 에 정의된 내용을 사용만 하겠다.
        List<? extends Object> list;


        /*
         * printList1 vs printList2
         */
        printList1(Arrays.asList(1, 2, 3));
        printList2(Arrays.asList(1, 2, 3));

        // 명확한 타입을 가진 리스트를 넣으면 에러가 난다.
        // List<Integer> 는 List<Object>의 서브타입이 아니다.
        List<Integer> list3 = Arrays.asList(1, 2, 3);
//        printList1(list3);
        // 이건 가능하다. 타입을 모르는 List에 명확한 타입을 가진 리스트를 넣을 수 있다.
        printList2(list3);
    }
}
