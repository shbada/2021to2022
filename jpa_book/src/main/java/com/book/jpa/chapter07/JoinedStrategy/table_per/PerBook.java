package com.book.jpa.chapter07.JoinedStrategy.table_per;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "BOOK_ID") // ID 재정의
public class PerBook extends PerItem {
    /*
    create table per_book (
       item_id int8 not null,
        name varchar(255),
        price int4 not null,
        author varchar(255),
        isbm varchar(255),
        primary key (item_id)
    )
     */
    private String author;
    private String isbm;
}
