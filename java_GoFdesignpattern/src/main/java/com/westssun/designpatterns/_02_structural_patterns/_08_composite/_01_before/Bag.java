package com.westssun.designpatterns._02_structural_patterns._08_composite._01_before;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Bag {

    @Getter
    private List<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }
}
