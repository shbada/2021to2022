package com.book.jpa.chapter06.D일대다;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamB {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /*
    create table memberb (
       id int8 not null,
        name varchar(10) not null,
        team_id int8,
        primary key (id)
    )

    create table teamb (
       team_id int8 not null,
        name varchar(255),
        primary key (team_id)
    )
     */
    /**
     * 일대다 단방향 관계 매핑일때 @JoinColumn 을 명시해야한다.
     * 그렇지않으면 중간에 하나의 테이블을 두고 연관관계를 관리하는 조인 테이블 전략으로 매핑하게된다.
     *
     * 단점) 매핑한 객체가 관리하는 외래키가 다른 테이블(Member)에 있다.
     * em.persist(member1); // INSERT member1
     * em.persist(member2); // INSERT member2
     * em.persist(team1); // INSERT team1 , UPDATE member1.fk UPDATE member2.fk
     *
     * UPDATE SQL 쿼리를 추가로 실행해야한다.
     *
     * 연관관계에 대한 정보를 Team 엔티티의 members 가 관리한다.
     * 따라서 Member 엔티티를 저장할때는 MEMBER 테이블의 TEAM_ID 외래키에 아무값도 저장되지 않는다.
     * 대신 Team 엔티티를 저장할때 Team.members 의 참조값을 확인해서 회원 테이블에 있는 TEAM_ID 외래 키를
     * 업데이트한다.
     *
     * 일대다 단방향 매핑 보다는 다대일 양방향 매핑을 사용하자.
     * 일대다 양방향은 존재하지 않는다.
     * -> @OneToMany 는 연관관계의 주인이 될 수 없다.
     * 관계형 데이터베이스의 특성상 일대다, 다대일 관계는 항상 다쪽에 FK가 존재한다.
     * 따라서 @OneToMany, @ManyToOne 둘중에 연관관계의 주인은 항상 다 쪽인 @ManyToOne을 사용한 곳이다.
     * 그런 이유로 @ManyToOne 에는 mappedBy 속성이 없다.
     *
     * 대신 Member 에 읽기전용으로 Team 추가는 가능하다.
     */
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<MemberB> memberB = new ArrayList<MemberB>();

}
