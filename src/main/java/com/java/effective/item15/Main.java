package com.java.effective.item15;

import java.lang.reflect.Array;

/**
 * https://devfunny.tistory.com/525
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(ArrayTest.VALUES[0]); // 1
        ArrayTest.VALUES[0] = 5;

        /* 변경이 된다 */
        System.out.println(ArrayTest.VALUES[0]); // 5

        System.out.println("------");

        // 변경시 에러
        // ArrayTest2.VALUE_LIST.set(0, 5);
        System.out.println(ArrayTest2.VALUE_LIST.get(0));

        System.out.println("------");

        /* copyArr 원소를 변경해도 ArrayTest3.VALUES 와는 무관 */
        int[] copyArr = ArrayTest3.getValues();
        System.out.println(copyArr[0]);
    }
}
