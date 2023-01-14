package com.designpattern.report._03_abstract_factory.step2_after;

import com.designpattern.report._03_abstract_factory.step2_after.abstractFactory.ShipFactory;
import com.designpattern.report._03_abstract_factory.step2_after.concreteFactory.WhitePartsProFactory;
import com.designpattern.report._03_abstract_factory.step2_after.concreteFactory.WhiteshipPartsFactory;
import com.designpattern.report._03_abstract_factory.step2_after.product.Ship;

public class ShipInventory {
    public static void main(String[] args) {
        ShipFactory shipFactory = new WhiteshipFactory(new WhiteshipPartsFactory());
        Ship ship = shipFactory.createShip();

        System.out.println(ship.getAnchor().getClass());
        System.out.println(ship.getWheel().getClass());

        ShipFactory shipProFactory = new WhiteshipFactory(new WhitePartsProFactory());
        Ship shipPro = shipProFactory.createShip();

        System.out.println(shipPro.getAnchor().getClass());
        System.out.println(shipPro.getWheel().getClass());
    }
}
