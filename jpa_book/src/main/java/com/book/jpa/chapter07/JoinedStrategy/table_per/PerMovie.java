package com.book.jpa.chapter07.JoinedStrategy.table_per;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class PerMovie extends PerItem {
    /*
    create table per_movie (
       item_id int8 not null,
        name varchar(255),
        price int4 not null,
        actor varchar(255),
        director varchar(255),
        primary key (item_id)
    )
     */
    private String director;
    private String actor;
}
