package com.designpattern.report._03_abstract_factory.step2_after.concreteFactory;

import com.designpattern.report._03_abstract_factory.step2_after.abstractFactory.ShipPartsFactory;
import com.designpattern.report._03_abstract_factory.step2_after.product.Anchor;
import com.designpattern.report._03_abstract_factory.step2_after.product.Wheel;
import com.designpattern.report._03_abstract_factory.step2_after.product.WhiteAnchorPro;
import com.designpattern.report._03_abstract_factory.step2_after.product.WhiteWheelPro;

public class WhitePartsProFactory implements ShipPartsFactory {
    @Override
    public Anchor createAnchor() {
        return new WhiteAnchorPro();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheelPro();
    }
}
