package com.book.jpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*
create table member (
    id varchar(255) not null,
    age int4,
    created_date timestamp,
    description oid,
    last_modified_date timestamp,
    role_type varchar(255),
    name varchar(10) not null,
    primary key (id)
)
 */
@Entity

/*
alter table if exists member
       add constraint NAME_AGE_UNIQUE unique (name, age)
 */
// DDL 을 자동 생성할때만 사용되고 JPA 실행 로직에는 영향을 주지 않는다.
// 따라서 스키마 자동 생성 기능을 사용하지 않고 DDL 을 만든다면 사용할 이유가 없다.
// 하지만 이 코드를 보고 명시적으로 제약 조건을 파악할 수 있다.
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint(
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"}
)})
@Getter
@Setter
public class Member {
    @Id
    @Column(name= "ID")
    // 데이터베이스에 따라 IDENTITY, SEQUENCE, TABLE 중 하나를 자동으로 선택한다.
    // @GeneratedValue 만 쓰면 default 는 AUTO 다.
    // TABLE 은 데이터베이스 시퀀스 생성용 테이블에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장한다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    private Integer age;

    /**
     * enum 을 사용한 회원의 타입 구분
     * 일반 회원 : USER
     * 관리자 : ADMIN
     *
     * member.setRoleType(RoleType.ADMIN);
     * DB 에 문자열로 저장된다. -> ADMIN
     *
     * EnumType.ORDINAL 은 0, 1로 정의 순서값이 저장되는데 이미 저장된 enum의 순서를 변경할 수가 없다.
     * STRING 으로 하는것이 안전하다.
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * @Temporal 생략시 timestamp 로 default 설정된다.
     * TemporalType.DATE : 날짜 (date date)
     * TemporalType.TIME : 시간 (time time)
     * TemporalType.TIMESTAMP : 날짜와 시간 (timestamp timestamp)
  간  */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * CLOB : String, char[], java.sql.CLOB (문자일 경우)
     * BLOB : byte[], java.sql.BLOB (그 외)
     */
    @Lob // CLOB 타입 에 매핑
    private String description;

    // 매핑하지 않는다. 객체에 임시로 어떤 값을 보관해야할 경우 사용한다.
    @Transient
    private Integer temp;
}
