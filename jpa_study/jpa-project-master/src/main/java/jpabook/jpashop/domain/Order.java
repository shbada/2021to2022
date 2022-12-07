package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    // @Column(name = "MEMBER_ID")
    // private Long memberId;

    /* 설계시에는 단방향만 우선 선언 */
    @ManyToOne  // MEMBER 1, ORDERS N
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // Order 기준) 여러개의 주문을 1명의 회원이 할 수 있다.

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL) // ALL:모두 적용, PERSIST:영속, REMOVE:삭제 
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    /* 양방향 추가 */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 연관관계의 주인은 ORDER
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
