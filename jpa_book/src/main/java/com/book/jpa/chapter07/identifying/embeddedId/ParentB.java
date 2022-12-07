package com.book.jpa.chapter07.identifying.embeddedId;

import javax.persistence.*;

/*
create table parentb (
        parent_id1 varchar(255) not null,
        parent_id2 varchar(255) not null,
        name varchar(255),
        primary key (parent_id1, parent_id2)
    )
 */
@Entity
public class ParentB {
    @EmbeddedId
    private ParentBId id;

    private String name; // ParentAId 의 id2 와 연결
}
