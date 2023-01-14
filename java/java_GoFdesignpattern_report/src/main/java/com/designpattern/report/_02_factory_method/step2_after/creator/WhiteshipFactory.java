package com.designpattern.report._02_factory_method.step2_after.creator;

import com.designpattern.report._02_factory_method.step2_after.creator.ShipFactory;
import com.designpattern.report._02_factory_method.step2_after.product.Ship;
import com.designpattern.report._02_factory_method.step2_after.product.Whiteship;

public class WhiteshipFactory implements ShipFactory {
    @Override
    public Ship createShip() {
        return new Whiteship();
    }
}
