package com.batch.order.order;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * ORDERS 의 총 합산 금액을 담는 클래스
 */
@Getter
public class OrderStatistics {

    private String amount; // 총 합산 금액

    private LocalDate date; // 해당 날짜

    @Builder
    private OrderStatistics(String amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }
}