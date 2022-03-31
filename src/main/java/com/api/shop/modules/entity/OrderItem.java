package com.api.shop.modules.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    private Long idx;

    // Order : Member 는 N : 1 이다. N인 곳에 FK가 존재. 그러므로 연관관계의 주인은 Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_idx")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_idx")
    private Item item;

    private int orderPrices; //  주문 가격

    private int count; // 주문 수량
}
