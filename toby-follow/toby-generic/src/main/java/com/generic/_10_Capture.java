package com.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _10_Capture {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 3, 2);
        reverse(list);
        System.out.println(list);
    }

    /*
      타입을 쓰지않으므로 wildcards로 바꿔보자.
     */
//    private static <T> void reverse(List<T> list) {
//        List<T> temp = new ArrayList<>(list);
//
//        for (int i = 0; i < list.size(); i++) {
//            list.set(i, temp.get(list.size() - i - 1));
//        }
//    }

    /*
      타입을 추론한다 -> 캡쳐
      wildcards 사용 : 캡쳐하는 상황에서는 캡처 가능한지 판단하고, 안되면 오류가 난다.
     */
    private static void reverse(List<?> list) {
//        List<?> temp = new ArrayList<>(list);
//
//        for (int i = 0; i < list.size(); i++) {
//            list.set(i, temp.get(list.size() - i - 1));
//        }

        reverseHelper(list);
    }

    /*
      Generic 타입으로 컴파일러가 자동으로 캡쳐한다.
      이걸 바로 사용하기에는 T를 사용한다는 오해가 있을 수 있다.
      T 타입을 실질적으로 사용하지 않으므로 wildcards 를 사용하는게 맞다.
      그래서 helper 의미의 메서드를 만들어서 사용하자.
     */
    private static <T> void reverseHelper(List<T> list) {
        List<T> temp = new ArrayList<>(list);

        for (int i = 0; i < list.size(); i++) {
            list.set(i, temp.get(list.size() - i - 1));
        }
    }

    /*
       이 방식도 가능
     */
    private static void reverseHelper2(List<?> list) {
        List temp = new ArrayList<>(list);
        List list2 = list;

        for (int i = 0; i < list.size(); i++) {
            list2.set(i, list2.get(list2.size() - i - 1));
        }
    }
}
