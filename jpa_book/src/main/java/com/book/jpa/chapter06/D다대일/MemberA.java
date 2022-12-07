package com.book.jpa.chapter06.D다대일;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


/**
 * Member / Team
 *
 * 회원은 team 을 알 수 있고, team 은 회원을 알 수 없다.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberA {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    /*
    create table member (
        id varchar(255) not null,
        name varchar(10) not null,
        team_id varchar(255),
        primary key (id)
    )
     */
    /**
     * 연관관계 매핑
     * 사실 여기의 주인의 단방향 매핑만으로도 객체의 연관관계 매핑은 이미 완료되었다.
     */
    @ManyToOne // member 테이블에 team_id varchar(255), 설정
    @JoinColumn(name="TEAM_ID") // 매핑할 외래 키 이름을 지정한다.
    private TeamA teamA;

    /**
     * 양방향 연관관계 set 메서드
     * @param teamA
     */
    public void setTeamA(TeamA teamA) {
        // 기존에 매핑된 팀이 있었다면 매핑 제거
        if (this.teamA != null) {
            this.teamA.getMemberA().remove(this);
        }

        this.teamA = teamA;
        teamA.getMemberA().add(this);
    }
}
