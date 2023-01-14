package com.java.effective.item19;

import java.time.Instant;

public class Sub extends Super {
    /* 생성자에서 초기화되는 필드 */
    private final Instant instant;
    public Sub() {
        instant = Instant.now(); /* 생성자 호출시 초기화 */
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);
    }

    public static void main(String[] args) {
        /**
         * 기댓값: 2번 호출
         * (상위클래스의 생성자에서 호출되는 overrideMe(); 로 인해 1번,
         * 그 다음 아래 sub.overrideMe(); 로 인해 2번.
         *
         * 결과값: 첫번째에 null 호출, 그 다음에 정상 호출.
         * 상위 클래스의 생성자는 하위 클래스의 생성자가 인스턴스 필드를 초기화하기도 전에 overrideMe 를 호출한다.
         */
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
