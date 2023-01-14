package com.book.jpa.chapter07.JoinedStrategy.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "BOOK_ID") // ID 재정의
public class Book extends Item {
    /*
    create table book (
       author varchar(255),
        isbm varchar(255),
        book_id int8 not null,
        primary key (book_id)
    )
     */
    private String author;
    private String isbm;
}
