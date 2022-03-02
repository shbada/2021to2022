package com.designpattern.report._02_factory_method.step2_after.creator;

import com.designpattern.report._02_factory_method.step2_after.creator.ShipFactory;
import com.designpattern.report._02_factory_method.step2_after.product.Blackship;
import com.designpattern.report._02_factory_method.step2_after.product.Ship;

public class BlackshipFactory implements ShipFactory {
    @Override
    public Ship createShip() {
        return new Blackship();
    }
}
