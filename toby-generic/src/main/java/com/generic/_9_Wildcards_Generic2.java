package com.generic;

import java.util.*;

public class _9_Wildcards_Generic2 {
    static <T> void method1(List<T> list, T t) { // 한번 정의된 T는 메서드의 여러 곳에서 사용 가능

    }

    static void method2(List<?> list) { // ? 타입을 내부에서 사용하지 않음
//        list.add(1); // 불가능
        list.add(null); // null만 add 가능하다.

        // 사용 가능 메서드
        list.size();
        list.clear();
        Iterator<?> iterator = list.iterator();
    }

    static void method3(List<Object> list) {

    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        method3(list); // List<Integer>는 List<Object>의 서브타입이 아니다.
        method2(list);

        System.out.println(isEmpty(list)); // 리스트가 비어있나?

        // 이럴때는 와일드카드 사용하는 frequency2를 사용하는게 맞다.
        // 제네릭 메서드로 선언하면 내부에서 T 타입으로 무언가를 사용하겠다는 의미다.
        // API 설계한 사람의 입장에서 의도를 올바르게 내놓지못한다.
        frequency(list, 3); // 3 이 몇번 나오나?
        frequency2(list, 3); // 3 이 몇번 나오나?

        // Integer (하위) > Number (상위)
        System.out.println(Collections.max(list,
                (Comparator<Number>) (a, b) -> a.toString().compareTo(b.toString())));
    }

    private static <T> long frequency(List<Integer> list, T elem) {
        return list.stream().filter(s -> s.equals(elem)).count();
    }

    // wildcards는 매개변수엔 사용 불가능 - 이럴때는 Object로 선언
    private static <T> long frequency2(List<?> list, Object elem) {
        // equals : Object 안에 정의되어있다.
        // equals 메서드는 wildcards 변수에 사용 가능하다.
        return list.stream().filter(s -> s.equals(elem)).count();
    }

    // 내부의 T에 관심이 있다. T를 다룬다. -> List<T>
    // wildcards - compareTo() 사용이나 Comparabler이 구현한 메서드까지만 사용하겠다 -> List<?>
    private boolean isEmpty3(List<? extends Comparable> list) {
        return true;
    }

    private static <T extends Comparable<T>> T max(List<T> list) {
        return list.stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).get(); // b가 크면 b
    }

    // 하위 한정 : 메서드 밖에있는 무언가에서 사용되기 위해서
    // 상위 한정 : list 가 메서드 내부에서 사용하기 위해서 넘겨지는 경우 사용 (값이 소모됨 a, b)
    // T 타입 기준으로 상위타입 : 하위타입 (하위타입을 상위타입에 넣어서 비교하는건 문제가 없다)
    private static <T extends Comparable<? super T>> T max2(List<? extends T> list) {
        return list.stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).get(); // b가 크면 b
    }

    private static <T> boolean isEmpty(List<T> list) {
        return list.size() == 0;
    }

    private static  boolean isEmpty2(List<?> list) {
        return list.size() == 0;
    }
}
