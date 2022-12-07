package com.java.effective.item79;

import java.util.HashSet;

public class Test {
    /**
     * 집합에 정숫값을 출력하다가, 그 값이 23이면 자기 자신을 제거하는 관찰자 추가
     * @param args
     */
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<>() {
            public void added(ObservableSet<Integer> s, Integer e) {
                System.out.println(e);

                /**
                 * 0 ~ 23 까지 출력 후 관찰자 자신을 구독해지한 다음 조용히 종료될 것이다.
                 * 라고 생각했겠지만, 23까지 출력 후 ConcurrentModificationException 이 발생한다.
                 */
                if (e == 23) {
                    s.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
