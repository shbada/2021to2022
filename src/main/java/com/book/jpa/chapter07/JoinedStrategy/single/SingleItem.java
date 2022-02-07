package com.book.jpa.chapter07.JoinedStrategy.single;

import javax.persistence.*;

/**
 * 단일테이블 전략
 *
 * [장점]
 * - 조인이 필요 없으므로 일반적으로 조회 성능이 빠르다
 * - 조회 쿼리가 단순하다
 *
 * [단점]
 * - 자식 엔티티가 매핑한 컬럼은 모두 null 을 허용해야한다
 * - 단일 테이블에 모든 것을 저장하므로 테이블이 커질 수 있다. 상황에 따라서 조회 성능이 오히려 느려질 수 있다.
 *
 * [특징]
 * - 구분 컬럼을 꼭 사용해야한다. @DiscriminatorColumn 을 꼭 설정해야한다.
 * - @DiscriminatorValue 를 지정하지 않으면 기본으로 엔티티 이름을 사용한다 (Movie, Album 등)
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분한다.
public abstract class SingleItem {
    /*
    create table single_item (
       dtype varchar(31) not null,
        item_id int8 not null,
        name varchar(255),
        price int4 not null,
        artist varchar(255),
        author varchar(255),
        isbm varchar(255),
        actor varchar(255),
        director varchar(255),
        primary key (item_id)
    )
     */
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}
