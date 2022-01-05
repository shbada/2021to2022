package com.example.jpa.practice;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id; // PK

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password; // varchar(255)

    private boolean emailVerified; // boolean

    private LocalDateTime emailCheckTokenGeneratedAt; // timestamp

    private LocalDateTime joinedAt; // timestamp

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage; // clob

    /**
     create table account_tags (
         account_id bigint not null,
         tags_id bigint not null,
         primary key (account_id, tags_id)
     )
     */
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    /**
     create table account_zones (
         account_id bigint not null,
         zones_id bigint not null,
         primary key (account_id, zones_id)
     )
     */
    @ManyToMany
    private Set<Zone> zones = new HashSet<>();
}
