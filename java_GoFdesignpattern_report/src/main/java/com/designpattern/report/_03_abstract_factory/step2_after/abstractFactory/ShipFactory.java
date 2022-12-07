package com.designpattern.report._03_abstract_factory.step2_after.abstractFactory;

import com.designpattern.report._03_abstract_factory.step2_after.product.Ship;

public interface ShipFactory {
    /**
     * 추상메서드
     * 하위클래스가 정의해야하는 메서드
     * @return
     */
    Ship createShip();
}
