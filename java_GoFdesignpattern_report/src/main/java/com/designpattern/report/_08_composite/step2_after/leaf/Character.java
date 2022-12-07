package com.designpattern.report._08_composite.step2_after.leaf;

import com.designpattern.report._08_composite.step2_after.composite.Bag;
import com.designpattern.report._08_composite.step2_after.component.Component;

public class Character implements Component {

    private Bag bag;

    @Override
    public int getPrice() {
        return bag.getPrice();
    }

}
