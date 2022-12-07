package com.book.jpa.chapter06.F다대다_한계극복;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 다대다 에서 컬럼이 추가되어, 별도의 엔티티로 분리
 */
@Entity
/*
식별자 클래스
JPA 에서 복합키를 사용하려면 별도의 식별자 클래스를 만들어야한다.

- 복합키는 별도의 식별자 클래스로 만들어야 한다.
- Serializable 을 구현해야한다.
- equals, hashCode 메소드를 구현해야한다.
- 기본 생성자가 있어야한다.
- 식별자 클래스는 public 이여야 한다.
- @IdClass 를 사용하는 방법 외에 EmbeddedId를 사용하는 방법도 있다.
 */
@IdClass(MemberProductId.class)
@Setter
@Getter
public class MemberProduct {
    /*
    create table member_product (
        member_id int8 not null,
        product_id int8 not null,
        order_amount int4 not null,
        primary key (member_id, product_id)
    )
     */
    /**
     * 회원과 상품의 기본키를 받아서 자신의 기본키로 사용한다.
     * 부모테이블의 기본키를 받아서 자신의 기본키+외래키로 사용하는 것을 '식별관계'라고 한다.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberF memberF;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private ProductF productF;

    /* 컬럼 추가 */
    private int orderAmount;
}
