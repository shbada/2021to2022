package study.querydsl.entity;

import lombok.*;
import javax.persistence.*;

/*
    @Setter: 실무에서 가급적 Setter는 사용하지 않기
    @NoArgsConstructor AccessLevel.PROTECTED: 기본 생성자 막고 싶은데, JPA 스팩상 PROTECTED로 열어두어야 함
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/* ToString 생성 (메서드를 자동으로 만들어주는데, "team" 은 들어가면 안된다. 무한루프를 타게된다.) */
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    /**
     * N:1 관계
     * 연관관계의 주인 : Member.team
     * Member 테이블에 team_id(FK) 생성
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    /**
     * 생성자 1
     * @param username
     */
    public Member(String username) {
        this(username, 0);
    }

    /**
     * 생성자 2
     * @param username
     * @param age
     */
    public Member(String username, int age) {
        this(username, age, null);
    }

    /**
     * 생성자 3
     * @param username
     * @param age
     * @param team
     */
    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    /**
     * Team 변경
     * @param team
     */
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
