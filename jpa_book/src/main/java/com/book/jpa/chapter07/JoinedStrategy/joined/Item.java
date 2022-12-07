package com.book.jpa.chapter07.JoinedStrategy.joined;

import javax.persistence.*;

/**
 * 조인 전략
 * 엔티티 각각을 모두 테이블로 만들고 자식 테이블이 부모 테이블의 기본 키를 받아서 기본키 + 외래 키로 사용하는 전략
 * - 조회할때 조인을 자주 사용
 * - 객체를 타입으로 구분할 수 있지만 테이블은 타입의 개념이 없으므로, 타입을 구분하는 컬럼이 필요하다. (예시 : DTYPE)
 *
 * [장점]
 * - 테이블이 정규화된다.
 * - 외래키 참조 무결성 제약조건을 활용할 수 있다.
 * - 저장 공간을 효율적으로 사용한다.
 *
 * [단점]
 * - 조회할때 조인이 많이 사용되므로 성능이 저하된다.
 * - 조회 쿼리가 복잡하다.
 * - 데이터를 등록할 INSERT SQL 을 두번 실행한다.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 매핑은 부모 클래스에 해당 어노테이션을 써야한다. 매핑전략 지정
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분한다.
public abstract class Item {
    /*
    create table item (
        dtype varchar(31) not null,
        item_id int8 not null,
        name varchar(255),
        price int4 not null,
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
