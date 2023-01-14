package com.book.jpa.chapter06.F다대다_한계극복;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * MemberProduct 를 Order 엔티티로 새로 생성하는게 편할 수도 있다.
 */
@Entity
@Setter
@Getter
public class Orders {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberG memberG;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductG productG;

    /* 컬럼 추가 */
    private int orderAmount;
}
