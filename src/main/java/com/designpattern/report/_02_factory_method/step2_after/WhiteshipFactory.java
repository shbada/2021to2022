package com.designpattern.report._02_factory_method.step2_after;

public class WhiteshipFactory implements ShipFactory {
    @Override
    public Ship createShip() {
        return new Whiteship();
    }
}
