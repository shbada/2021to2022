package com.designpattern.report._08_composite.step1_before;

import lombok.Getter;

public class Item {

    private String name;

    @Getter
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
