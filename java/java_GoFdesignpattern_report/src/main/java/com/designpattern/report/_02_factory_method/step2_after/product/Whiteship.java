package com.designpattern.report._02_factory_method.step2_after.product;

import com.designpattern.report._02_factory_method.step2_after.product.Ship;

public class Whiteship extends Ship {
    public Whiteship() {
        setName("whiteship");
        setLogo("\uD83D\uDEE5");
        setColor("white");
    }
}
