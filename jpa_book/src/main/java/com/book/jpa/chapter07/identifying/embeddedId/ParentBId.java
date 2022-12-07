package com.book.jpa.chapter07.identifying.embeddedId;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * @Embeddable 어노테이션을 붙여야한다.
 * Serializable 구현해야한다.
 * equals, hashCode 구현해야한다.
 * 기본 생성자가 있어야한다.
 * 식별자 클래스는 public 이여야 한다.
 *
 *
 * 조회쿼리
 * ParentB parentB = new ParentB();
 * ParentBId parentId = new PArentBId("myId1", "myId2");
 * parentB.setId(parentId);
 * parentB.setName("parentName");
 *
 *
 * JPQL 에서
 * @embddedId
 * : "select p.id.id1, p.id.id2 from Parentb p"
 *
 * @IdClass
 * : "select p.id1, p.id2 from Parenta p"
 */
@Embeddable
@EqualsAndHashCode
public class ParentBId implements Serializable {
    @Column(name = "PARENT_ID1")
    private String id1;

    @Column(name = "PARENT_ID2")
    private String id2;

    public ParentBId() {

    }

    public ParentBId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }
}
