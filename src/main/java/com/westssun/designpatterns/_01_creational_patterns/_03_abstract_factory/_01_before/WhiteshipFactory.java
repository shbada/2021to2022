package com.westssun.designpatterns._01_creational_patterns._03_abstract_factory._01_before;

import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.DefaultShipFactory;
import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.Ship;
import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.Whiteship;

public class WhiteshipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        Ship ship = new Whiteship();
        /* 구현클래스를 직접 - 구현클래스 변경시 계속적으로 코드 변경이 발생한다. */
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
