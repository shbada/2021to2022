package com.book.jpa.chapter05;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Team {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String name;

    /*
    create table team_members (
        team_id varchar(255) not null,
        members_id varchar(255) not null
    )
     */
    // @OneToMany
    // private List<Member> members;
}
