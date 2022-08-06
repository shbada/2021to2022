package study.querydsl.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
Member와 Team은 양방향 연관관계,
Member.team 이 연관관계의 주인,
Team.members 는 연관관계의 주인이 아님,
따라서 Member.team 이 데이터베이스 외래키 값을 변경, 반대편은 읽기만 가능
 */
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;

    private String name;

    /**
     * team.members 는 연관관계 주인이 아니다.
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    /**
     * 생성자
     * @param name
     */
    public Team(String name) {
        this.name = name;
    }
}
