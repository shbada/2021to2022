package com.concurrency.chapter05.예제5_2_클라이언트락_동기화;

import java.util.Vector;

public class Main {
    /**
     * 클라이언트 측의 락을 사용
     * @param list
     * @return
     */
    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }

    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("a");
        vector.add("b");
        vector.add("c");
        vector.add("d");

        deleteLast(vector);
        getLast(vector);

        /**
         * 반복문을 실행하는 동안 동기화시키기위해 락을 사용하면 반복문이 실행되는 동안에는
         * Vector 클래스 내부의 값을 변경하는 모든 스레드가 대기 상태에 들어간다. -> 성능 저하
         * 반복문이 실행되는 동안 병렬 프로그래밍의 장점이 사라진다.
         */
//        synchronized (vector) {
//            for (int i = 0; i < vector.size(); i++) {
//                doSomething(vector.get(i));
//            }
//        }
    }
}
