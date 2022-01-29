package com.book.jpa.chapter06.D일대일;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberC {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    /*
    create table memberc (
        id int8 not null,
        name varchar(10) not null,
        locker_id int8,
        primary key (id)
    )
     */
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private LockerC lockerC;
}
