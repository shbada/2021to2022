package com.westssun.designpatterns._03_behavioral_patterns._21_strategy._02_after;

import java.util.Collections;
import java.util.Comparator;

/**
 * 전략 패턴
 *
 * [장점]
 * 새로운 전략을 추가하더라도 기존 코드를 변경하지 않는다.
 * 상속 대신 위임을 사용할 수 있다.
 * 런타임에 전략을 변경할 수 있다.
 *
 * [단점]
 * 복잡도가 증가한다.
 * 클라이언트 코드가 구체적인 전략을 알아야한다.
 */
public class Client {

    public static void main(String[] args) {
        BlueLightRedLight game = new BlueLightRedLight();

        // 새로운 전략은 클래스 추가 필요 (기존 클래스(BlueLightRedLight)의 수정은 필요 없다)
        game.blueLight(new Normal());
        game.redLight(new Fastest());
        game.blueLight(new Speed() {
            @Override
            public void blueLight() {
                System.out.println("blue light");
            }

            @Override
            public void redLight() {
                System.out.println("red light");
            }
        });
    }
}
