package com.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _8_Wildcards_Generic {
    static class A {}
    static class B extends A {}

    static void print(List<? extends Object> list) {}

    public static void main(String[] args) {
        List<B> lb = new ArrayList<B>();
        // 불가능
        // List<B> 는 List<A>의 서브타입이 아니다.
//        List<A> la = lb;

        // 가능
        // List<? extends Object> != List<Object>
        List<Integer> listInt = Arrays.asList(1, 2, 3);
        print(listInt);

        // 이건 불가능한데,
//        List<A> la = lb;
        // 이건 가능하다.
        List<? extends A> la = lb;
        // A를 상속한 어떤 타입이던지 모두 가능한 리스트에 A 타입 객체를 넣을 수 없다?
//        la.add(new A()); // A 타입의 객체는 넣을 수 없다.(캡쳐가 안된다)
//        la.add(new B()); // B도 안들어간다.
        la.add(null); // add 할수있는건 null 뿐이다.

        List<? super B> l2 = lb;
        // 불가능하다.
//        List<? super A> l3 = lb; // 슈퍼타입이 될 수 없으니

    }
}
