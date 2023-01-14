package com.westssun.designpatterns._01_creational_patterns._03_abstract_factory._02_after;

/**
 * 추상 팩토리 생성
 *
 * - 여러가지 제품을 다루고있는 점이 존재 --> 싱글 원칙 위반 이라고 볼수도..
 *
 *  팩토리 메서드 패턴과 매우 흡사하다.
 *  둘다 구체적인 객체 생성 과정을 추상화한 인터페이스를 제공한다.
 *
 *  팩토리 메서드 패턴 - 팩토리를 구현하는 방법 (inheritance)
 *  > 구체적인 생성 과정을 하위 또는 구체적인 클래스로 옮기는것이 목적
 *
 *  추상 팩토리 패턴 - 팩토리를 사용하는 방법  (composition)
 *  > 관련있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들 수 있께 해주는것이 목적
 */
public interface ShipPartsFactory {
    Anchor createAnchor();

    Wheel createWheel();
}
