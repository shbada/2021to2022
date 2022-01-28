package com.redo.studyolle.modules.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/*
  create table study (
       id int8 not null,
        closed boolean not null,
        closed_date_time timestamp,
        full_description oid,
        image oid,
        member_count int4 not null,
        path varchar(255),
        published boolean not null,
        published_date_time timestamp,
        recruiting boolean not null,
        recruiting_updated_date_time timestamp,
        short_description varchar(255),
        title varchar(255),
        use_banner boolean not null,
        primary key (id)
    )
 */
@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Study {

    @Id @GeneratedValue
    private Long id;

    /*
     create table study_managers (
       study_id int8 not null,
        managers_id int8 not null,
        primary key (study_id, managers_id)
    )
     */
    @ManyToMany
    private Set<Account> managers = new HashSet<>();

    /*
     create table study_members (
       study_id int8 not null,
        members_id int8 not null,
        primary key (study_id, members_id)
    )
     */
    @ManyToMany
    private Set<Account> members = new HashSet<>();

    @Column(unique = true)
    private String path;

    private String title;

    private String shortDescription;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String image;

    /*
    create table study_tags (
       study_id int8 not null,
        tags_id int8 not null,
        primary key (study_id, tags_id)
    )
     */
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();

    /*
    create table study_zones (
       study_id int8 not null,
        zones_id int8 not null,
        primary key (study_id, zones_id)
    )
     */
    @ManyToMany
    private Set<Zone> zones = new HashSet<>();

    private LocalDateTime publishedDateTime;

    private LocalDateTime closedDateTime;

    private LocalDateTime recruitingUpdatedDateTime;

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    private boolean useBanner;

    private int memberCount;
}
