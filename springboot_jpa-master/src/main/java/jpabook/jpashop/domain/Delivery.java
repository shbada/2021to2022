package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue /* 시퀀스 사용 */
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) /* 연관관계 주인이 아니다. 조회용 */
    private Order order;

    @Embedded /* 내장타입 */
    private Address address;

    // @Enumerated(EnumType.ORDINAL) /* ORDINAL 은 숫자로 들어간다. - 중간에 다른 상태가 들어오면 망한다. */
    @Enumerated(EnumType.STRING) // 꼭 스트링으로 넣자. ORDINAL 은 사용하지말자.
    private DeliveryStatus status; // READY, COMP

}
