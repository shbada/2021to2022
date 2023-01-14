package com.book.jpa.chapter07.JoinedStrategy.joined;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
    /*
    create table movie (
        actor varchar(255),
        director varchar(255),
        item_id int8 not null,
        primary key (item_id)
    )
     */
    private String director;
    private String actor;
}
