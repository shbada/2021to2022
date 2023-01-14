package com.java.effective.item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private final static List<Cheese> cheesesInStock = new ArrayList<>();

    private final static Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

    /**
     * 재고가 없다면 null 반환
     * @return
     */
    public static List<Cheese> getCheeses() {
        return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
    }

    public static Cheese[] getCheese() {
        return cheesesInStock.toArray(new Cheese[0]);
    }

    public static void main(String[] args) {
        List<Cheese> cheeses = getCheeses();

        if (cheeses != null && cheeses.contains(Cheese.STILTON)) {
            System.out.println("ok");
        }
    }

    enum Cheese {
        STILTON
    }
}
