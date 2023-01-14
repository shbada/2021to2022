package com.book.jpa.chapter07.MappedSuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;

/**
 * 매핑정보 재정의 : @AttributeOverrides, @AttributeOverride
 * 연관관계 재정의 : @AssociationOverrides, @AssociationOverride
 */
// 부모에게 물려받은 매핑 정보를 재정의할 수도 있다.
@AttributeOverride(name = "od", column = @Column(name = "MAPPED_MEMBER_ID"))
public class MappedSeller extends BaseEntity {
    private String shopName;
}
