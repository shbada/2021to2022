package com.book.jpa.chapter05;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Member / Team
 *
 * 회원은 team 을 알 수 있고, team 은 회원을 알 수 없다.
 */
@Entity
@Getter
@Setter
public class Member {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
}
