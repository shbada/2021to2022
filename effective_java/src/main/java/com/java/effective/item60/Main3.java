package com.java.effective.item60;

import java.math.BigDecimal;

public class Main3 {
    public static void main(String[] args) {
        int itemsBought = 0;
        int funds = 100;

        for (int price = 10; funds >= price; price += 10) {
            funds = funds - price;
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(달러): " + funds);
    }
}
