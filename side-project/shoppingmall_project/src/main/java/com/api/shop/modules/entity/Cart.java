package com.api.shop.modules.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "idx")
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_idx")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    private int totalPrices; //  주문 가격

    private int count; // 주문 수량

    /**
     * 장바구니 등록
     * @param member
     * @param item
     * @param count
     * @return
     */
    public static Cart addCart(Member member, Item item, int count) {
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItem(item);
        cart.setCount(count);

        cart.setTotalPrices(item.getPrice() * count);

        return cart;
    }
}
