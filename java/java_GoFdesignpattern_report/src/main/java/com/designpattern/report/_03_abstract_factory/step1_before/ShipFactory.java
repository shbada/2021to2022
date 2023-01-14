package com.designpattern.report._03_abstract_factory.step1_before;

public interface ShipFactory {
    /**
     * 추상메서드
     * 하위클래스가 정의해야하는 메서드
     * @return
     */
    Ship createShip();
}
