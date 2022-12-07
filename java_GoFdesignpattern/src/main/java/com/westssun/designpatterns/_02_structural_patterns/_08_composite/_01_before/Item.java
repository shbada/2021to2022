package com.westssun.designpatterns._02_structural_patterns._08_composite._01_before;

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
