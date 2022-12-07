package com.book.jpa.chapter07.JoinedStrategy.single;

import com.book.jpa.chapter07.JoinedStrategy.joined.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
public class SingleBook extends SingleItem {
    private String author;
    private String isbm;
}
