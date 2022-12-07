package com.designpattern.report._03_abstract_factory.step2_after.concreteFactory;

import com.designpattern.report._03_abstract_factory.step2_after.abstractFactory.ShipPartsFactory;
import com.designpattern.report._03_abstract_factory.step2_after.product.Anchor;
import com.designpattern.report._03_abstract_factory.step2_after.product.Wheel;
import com.designpattern.report._03_abstract_factory.step2_after.product.WhiteAnchor;
import com.designpattern.report._03_abstract_factory.step2_after.product.WhiteWheel;

public class WhiteshipPartsFactory implements ShipPartsFactory {
    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
