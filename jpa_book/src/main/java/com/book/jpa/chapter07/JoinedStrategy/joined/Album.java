package com.book.jpa.chapter07.JoinedStrategy.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 구분 컬럼에 저장될 값을 지정한다.
public class Album extends Item {
    /*
    create table album (
       artist varchar(255),
        item_id int8 not null,
        primary key (item_id)
    )
     */
    private String artist;
}
