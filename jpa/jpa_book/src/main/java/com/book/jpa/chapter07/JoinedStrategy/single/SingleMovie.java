package com.book.jpa.chapter07.JoinedStrategy.single;

import com.book.jpa.chapter07.JoinedStrategy.joined.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class SingleMovie extends SingleItem {
    private String director;
    private String actor;
}
