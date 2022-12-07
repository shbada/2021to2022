package com.designpattern.report._03_abstract_factory.step1_before;

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
