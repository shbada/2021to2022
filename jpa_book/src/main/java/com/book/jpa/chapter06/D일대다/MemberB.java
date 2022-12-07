package com.book.jpa.chapter06.D일대다;

import com.book.jpa.chapter05.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberB {
    @Id
    @Column(name= "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 10)
    private String username;

    /**
     * 읽기만 가능하다.
     * 양방향처럼 보이는 방법이다.
     * TeamB 의 TEAM_ID 를 동일하게 매핑하므로 둘다 같은 키를 관리하게되어 문제가 발생할 수 있다.
     * 따라서 insertable, updatable 을 사용하여 읽기전용으로 만든다.
     *
     * 이 방법 보다는, 다대일 양방향 매핑을 사용하는 것이 좋다.
     */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private TeamB teamB;
}
