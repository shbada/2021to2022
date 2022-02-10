package com.westssun.designpatterns._03_abstract_factory._02_after;

import com.westssun.designpatterns._02_factory_method._02_after.Ship;
import com.westssun.designpatterns._02_factory_method._02_after.ShipFactory;

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
