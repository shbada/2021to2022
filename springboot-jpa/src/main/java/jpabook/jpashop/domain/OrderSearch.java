package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 화면 검색 조건
 */
@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
