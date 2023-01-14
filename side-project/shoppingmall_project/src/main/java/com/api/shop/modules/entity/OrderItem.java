package com.api.shop.modules.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    /**
     * 주문 아이템 생성
     * @param item
     * @param price
     * @param itemCount
     * @return
     */
    public static OrderItem createOrderItem(Item item, int price, int itemCount) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrices(price);
        orderItem.setCount(itemCount);

        // 재고 수량 계산
        item.removeStock(itemCount);

        return orderItem;
    }

    /**
     * 주문 상품 전체 가격 조회
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrices() * getCount();
    }
}
