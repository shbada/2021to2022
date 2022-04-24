package com.designpattern.report._03_abstract_factory.step2_after;

import com.designpattern.report._03_abstract_factory.step2_after.abstractFactory.DefaultShipFactory;
import com.designpattern.report._03_abstract_factory.step2_after.abstractFactory.ShipPartsFactory;
import com.designpattern.report._03_abstract_factory.step2_after.product.Ship;
import com.designpattern.report._03_abstract_factory.step2_after.product.Whiteship;

/**
 * Client
 */
public class WhiteshipFactory extends DefaultShipFactory {

    private ShipPartsFactory shipPartsFactory;

    // WhiteshipPartsFactory, WhitePartsProFactory
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
