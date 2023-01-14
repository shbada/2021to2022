package com.westssun.designpatterns._01_creational_patterns._03_abstract_factory._02_after;

import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.DefaultShipFactory;
import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.Ship;
import com.westssun.designpatterns._01_creational_patterns._02_factory_method._02_after.Whiteship;

public class WhiteshipFactory extends DefaultShipFactory {

    private ShipPartsFactory shipPartsFactory;

    public WhiteshipFactory(ShipPartsFactory shipPartsFactory) {
        this.shipPartsFactory = shipPartsFactory;
    }

    /*
    구현클래스의 변경이 발생해도, 해당 코드에 변경은 발생하지 않는다.
     */
    @Override
    public Ship createShip() {
        Ship ship = new Whiteship();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
