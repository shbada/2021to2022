package com.book.jpa.chapter06.D일대일;

import com.book.jpa.chapter05.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LockerD {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String name;

    /*
    create table lockerd (
       id int8 not null,
        name varchar(10) not null,
        member_id int8,
        primary key (id)
    )
     */
    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private MemberD memberD;
}
