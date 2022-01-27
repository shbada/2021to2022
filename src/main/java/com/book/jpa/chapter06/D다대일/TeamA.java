package com.book.jpa.chapter06.D다대일;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TeamA {
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

    /**
     * 양방향 연관관계 매핑시
     * 두 연관관계 중 하나를 연관관계 주인으로 정해야한다.
     * 연관관계의 주인만이 데이터베이스 연관관계와 매핑되고 외래키를 관리(등록/수정/삭제) 할 수 잇다.
     * 반면에 주인이 아닌쪽(team)은 읽기만 할 수 있다.
     *
     * 1) 주인(Member)는 mappedBy 속성을 사용하지 않는다.
     * 2) 주인이 아니면(Team) mappedBy 속성을 사용해서 값으로 연관관계의 주인을 지정해야한다.
     */
     @OneToMany(mappedBy = "team") // team 은 주인이 아님을 설장한다. 주인은 Member.team 이다.
     private List<MemberA> memberA;

     public void addMember(MemberA memberA) {
         this.memberA.add(memberA);

         if (memberA.getTeamA() != this) {
             memberA.setTeamA(this);
         }
     }
}
