package com.book.jpa.chapter07.JoinedStrategy.table_per;

import javax.persistence.*;

/**
 * 구현 클래스마다 테이블 전략
 *
 * [장점]
 * - 서브 타입을 구분해서 처리할때 효과적이다.
 * - not null 제약조건을 사용할 수 있다.
 *
 * [단점]
 * - 여러자식 테이블을 함께 조회할때 성능이 느리다. (SQL에 UNION을 사용해야한다)
 * - 자식 테이블을 통합해서 쿼리하기 어렵다.
 *
 * [특징]
 * - 구분 컬럼을 사용하지 않는다.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "DTYPE") // 부모 클래스에 구분 컬럼을 지정한다. 이 컬럼으로 저장된 자식 테이블을 구분한다.
public abstract class PerItem {
    /*
    x
     */
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
}
