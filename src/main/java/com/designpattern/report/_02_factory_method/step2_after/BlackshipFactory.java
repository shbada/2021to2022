package com.designpattern.report._02_factory_method.step2_after;

public class BlackshipFactory implements ShipFactory {
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
