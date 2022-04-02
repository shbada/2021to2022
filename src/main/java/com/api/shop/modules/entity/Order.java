package com.api.shop.modules.entity;

import com.api.shop.modules.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long idx;

    // Order : Member 는 N : 1 이다. N인 곳에 FK가 존재. 그러므로 연관관계의 주인은 Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 (enum - ORDER, CANCEL)

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Embedded /* 내장타입 */
    private Address address;

    private LocalDateTime orderDate;

    private int orderTotalPrices; //  주문 가격

    /**
     * OrderItem set Order
     * @param orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItemList.add(orderItem);
        orderItem.setOrder(this);
    }

    /**
     * 주문 생성
     * @param member
     * @param address
     * @param orderItem
     * @return
     */
    public static Order createOrder(Member member, Address address, OrderItem orderItem) {
        Order order = new Order();
        order.setMember(member);
        order.setAddress(address);
        order.addOrderItem(orderItem);
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());

        order.setOrderTotalPrices(orderItem.getTotalPrice());
        return order;
    }
}
