package com.example.jpa.practice;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Study {

    @Id @GeneratedValue
    private Long id;

    /**
     create table study_managers (
         study_id bigint not null,
         managers_id bigint not null,
         primary key (study_id, managers_id)
     )
     */
    @ManyToMany
    private Set<Account> managers = new HashSet<>();

    /**
     create table study_members (
         study_id bigint not null,
         members_id bigint not null,
         primary key (study_id, members_id)
     )
     */
    @ManyToMany
    private Set<Account> members = new HashSet<>();

    @Column(unique = true)
    private String path;

    private String title;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String fullDescription; // clob

    @Lob @Basic(fetch = FetchType.EAGER)
    private String image; // clob

    /**
     create table study_tags (
         study_id bigint not null,
         tags_id bigint not null,
         primary key (study_id, tags_id)
     )
     */
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    /**
     create table study_zones (
         study_id bigint not null,
         zones_id bigint not null,
         primary key (study_id, zones_id)
     )
     */
    @ManyToMany
    private Set<Zone> zones = new HashSet<>();

    private LocalDateTime publishedDateTime;

    private boolean recruiting;

    private int memberCount;
}
