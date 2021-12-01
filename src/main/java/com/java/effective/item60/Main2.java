package com.java.effective.item60;

import java.math.BigDecimal;

public class Main2 {
    public static void main(String[] args) {
        final BigDecimal TEN_CNETS = new BigDecimal("0.10");

        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");

        for (BigDecimal price = TEN_CNETS;
             funds.compareTo(price) >= 0; price = price.add(TEN_CNETS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }

        System.out.println(itemsBought + "개 구입");
        System.out.println("잔돈(달러): " + funds);
    }
}
