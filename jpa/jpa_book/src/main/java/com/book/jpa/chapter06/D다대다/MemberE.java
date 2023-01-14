package com.book.jpa.chapter06.D다대다;

import com.book.jpa.chapter06.D일대다.TeamB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberE {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    /*
    create table member_product_e (
       member_id int8 not null,
        product_id int8 not null
    )
     */
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT_E", // 연결 테이블을 지정
               joinColumns = @JoinColumn(name = "MEMBER_ID"), // 현재 방향인 회원과 매핑할 조인 컬럼 정보
               inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")) // 반대 방향인 상품과 매핑할 조인 컬럼 정보
    private List<ProductE> productEList = new ArrayList<>();

    // 양방향일때 편의 메소드 추가
    public void addProduct(ProductE productE) {
        productEList.add(productE);
        productE.getMemberEList().add(this);
    }
}
