package com.book.jpa.chapter06.D일대일;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberD {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    @OneToOne(mappedBy = "memberD")
    private LockerD lockerD;
}
