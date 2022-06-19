package com.book.jpa.chapter06.F다대다_한계극복;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
public class MemberG {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    @OneToMany(mappedBy = "memberG")
    private List<Orders> orders = new ArrayList<>();
}
