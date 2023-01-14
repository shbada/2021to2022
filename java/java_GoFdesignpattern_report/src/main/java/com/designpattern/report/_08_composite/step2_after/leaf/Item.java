package com.designpattern.report._08_composite.step2_after.leaf;

import com.designpattern.report._08_composite.step2_after.component.Component;

public class Item implements Component {

    private String name;

    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int getPrice() {
        return this.price;
    }
}
