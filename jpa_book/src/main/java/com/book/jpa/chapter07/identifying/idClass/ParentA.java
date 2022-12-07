package com.book.jpa.chapter07.identifying.idClass;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/*
create table parenta (
       parent_id1 varchar(255) not null,
        parent_id2 varchar(255) not null,
        name varchar(255),
        primary key (parent_id1, parent_id2)
    )
 */
@Entity
@IdClass(ParentAId.class)
@Setter
public class ParentA {
    @Id
    @Column(name = "PARENT_ID1")
    private String id1; // ParentAId 의 id1 과 연결

    @Id
    @Column(name = "PARENT_ID2")
    private String id2;

    private String name; // ParentAId 의 id2 와 연결
}
