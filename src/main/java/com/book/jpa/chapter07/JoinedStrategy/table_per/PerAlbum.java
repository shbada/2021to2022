package com.book.jpa.chapter07.JoinedStrategy.table_per;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A") // 구분 컬럼에 저장될 값을 지정한다.
public class PerAlbum extends PerItem {
    /*
    create table per_album (
       item_id int8 not null,
        name varchar(255),
        price int4 not null,
        artist varchar(255),
        primary key (item_id)
    )
     */
    private String artist;
}
