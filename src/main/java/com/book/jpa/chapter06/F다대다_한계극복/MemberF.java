package com.book.jpa.chapter06.F다대다_한계극복;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Member_product 테이블에 다른 컬럼이 필요한 경우
 * ORDERAMOUNT
 * ORDERDATE
 *
 * 이렇게 컬럼을 추가하면 더는 @ManyToMany를 사용할 수 없다.
 * -> 주문 엔티티나 상품 엔티티에서 추가한 컬럼들을 매핑할 수 없다.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberF {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    /**
     * 역방향 매핑
     * 연관관계의 주인은 MemberProduct
     * 따라서 mappedBy는 연관관계의 주인이 아닌 member 로 지정
     *
     * 상품 엔티티(ProductD) 에서는 회원상품 엔티티로 객체 그래프 탐색 기능이 필요하지 않음
     */
    @OneToMany(mappedBy = "memberF")
    private List<MemberProduct> memberProducts;
}
