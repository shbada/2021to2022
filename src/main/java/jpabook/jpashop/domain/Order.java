package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name= "order_id")
    private Long id;

    /**
     * Order와 Member는 N:1 관계
     * 연관관계 주인 정하기 : FK가 ORDERS에 MEMBER_ID 이기 때문에 FK가 있는 ORDERS가 주인테이블이다.
     */
    // xxToOne 는 default 가 EAGER
    @ManyToOne(fetch = FetchType.LAZY) // 기본이 즉시로딩이므로, 지연로딩으로 설정하자.
    // 아래 즉시로딩 문제 : N + 1개의 쿼리가 더 날라간다.
    // @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩 : Order 조회시 Member 를 항상 조인하여 함께 가져온다. (무조건 지연로딩을 사용하자(LAZY))
    // @XToOne(OneToOne, ManyToOne) 관계는 기본이 즉시로딩이므로 지연로딩으로 설정하자.
    @JoinColumn(name = "member_id")
    private Member member;

    // xxToMany 는 default 가 LAZY
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) /* 조회용이다. order 필드에 의해 매핑되었다. 연관관계 주인이 아니다. */
    // cascade
    // persist(orderItemA) persist(orderItemB) persist(orederItemC) persis(order) 이렇게 각각 호출을 해야하는데
    // cascade가 있으면 persist(order)만 있으면 orderItemA, orderItemB, orderItemC를 모두 저장해준다.

    // 컬렉션은 필드에서 초기화하자. null 문제에서 안전하다. (초기화에 안전하다)
    // 하이버네이터가 컬렉션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경한다. (persist() 후에 ArrayList -> collection.internal.PersistenBag 이런식으로 바뀐다)
    // 그래서 setxx()는 만들지말고, 이 컬렉션을 가급적이면 변경하지말자.
    private List<OrderItem> orderItems = new ArrayList<>();

    // FK 를 어디에 둬도 상관없다. 1:1
    // 접근성이 더 많은 곳에 놓자. 그렇다면 ORDER에 두겠다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // ORDER 를 저장할때 Delivery 도 저장해주겠다는 의미
    @JoinColumn(name = "delivery_id") /* 연관 관계의 주인 */
    private Delivery delivery;

    //private Date date; 이걸 사용하면 DateType 등 매핑이 필요하다. java8 부터는 LocalDateTime 이 편하다.

    private LocalDateTime orderDate; // 주문시간 (DB에는 order_date 로 들어간다.)

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 (enum - ORDER, CANCEL)

    // 주문하면 Member 도 setting 해줘야한다.
    // 연관관계 편의 메서드 (양방향 관계일때 사용하기 좋다. 컨트롤하는 클래스 안에다 넣자.)
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); // setMember 호출순간 셋팅되어야할 데이터가 셋팅된다.
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //== 생성 메서드 ==//
    /* 주문 생성 */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        Arrays.stream(orderItems).forEach(order::addOrderItem);

        order.setStatus(OrderStatus.ORDER); // 주문상태
        order.setOrderDate(LocalDateTime.now()); // 현재날짜
        return order;
    }

    /* 생성자 생성을 막는다. */
    protected  Order() {}

    //== 비즈니스 로직 메서드 ==//

    /**
     * 주문 취소
     */
    public void cancel() {
        // 배송중 상태 체크
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);

        // orderItem 이 여러개일 경우가 있으므로
        orderItems.forEach(OrderItem::cancel);
    }

    //== 조회 로직 메서드 ==//
    /**
     * 전체 주문 가격 조회
     * @return
     */
    public int getTotalPrice() {
        // orderItem 의 totalPrice 호출 (주문 수량이 필요하기 때문에)
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}
