package com.book.jpa.chapter07.identifying.idClass;

import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * Serializable 구현해야한다.
 * equals, hashCode 구현해야한다.
 * 기본 생성자가 있어야한다.
 * 식별자 클래스는 public 이여야 한다.
 *
 * 조회쿼리
 * ParentA parentA1 = new ParentA();
 * parentA1.setId1("myId1");
 * parentA1.setId2("myId2");
 * parentA1.setName("parentName");
 *
 *
 * ParentA parentA2 = new ParentA();
 * parentA2.setId1("myId1");
 * parentA2.setId2("myId2");
 * parentA2.setName("parentName");
 *
 * 참고로 여기서,
 * parentA1 == parentA2 (true 가 나와야한다.)
 * 그래서 equals 를 재정의 해주는거다.
 * 자바에서는 Object 의 equals()이므로 false 가 나온다.
 */
public class ParentAId implements Serializable {
    private String id1;
    private String id2;

    public ParentAId() {

    }

    public ParentAId(String id1, String id2) {
        this.id1 = id1;
        this.id2 = id2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentAId parentAId = (ParentAId) o;
        return Objects.equals(id1, parentAId.id1) && Objects.equals(id2, parentAId.id2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id1, id2);
    }

    public static void main(String[] args) {
        ParentA parentA1 = new ParentA();
        parentA1.setId1("myId1");
        parentA1.setId2("myId2");
        parentA1.setName("parentName");


        ParentA parentA2 = new ParentA();
        parentA2.setId1("myId1");
        parentA2.setId2("myId2");
        parentA2.setName("parentName");

        System.out.println(parentA1 == parentA2); // true 가 나와야한다.
    }
}
