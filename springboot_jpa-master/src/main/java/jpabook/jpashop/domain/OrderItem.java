package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    /* 하나의 주문은 여러개의 주문 상품을 가질 수 있다. */
    /* FK : ORDER_ID 이므로 연관관계의 주인은 ORDER_ITEM */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrices; //  주문 가격

    private int count; // 주문 수량

    /* 생성자 생성을 막는다. */
    protected  OrderItem() {}

    //== 생성 메서드 ==//
    /* OrderItem 생성 */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrices(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count); // 수량만큼 재고 감소 시키기
        return orderItem;
    }

    //== 비즈니스 로직 메서드 ==//

    /**
     * 주문 취소에 따른 재고 수량 정정
     */
    public void cancel() {
        // 재고수량 원복 (주문 수량만큼)
        getItem().addStock(count); // JPA 가 update 쿼리를 직접 해준다.
    }

    //== 조회 로직 메서드 ==//

    /**
     * 주문상품 전체 가격 조회
     * @return
     */
    public int getTotalPrice() {
        return getOrderPrices() * getCount();
    }
}
