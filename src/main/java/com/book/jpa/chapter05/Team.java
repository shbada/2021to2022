package com.book.jpa.chapter05;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String name;
}
