package com.book.jpa.chapter07.JoinedStrategy.single;

import com.book.jpa.chapter07.JoinedStrategy.joined.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "BOOK_ID") // ID 재정의
public class SingleBook extends SingleItem {
    private String author;
    private String isbm;
}
