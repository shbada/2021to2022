package com.seohae.java.member.dto.entity;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long userIdx; /* pk */

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId; /* userId */

    @Column(name = "user_name", nullable = false)
    private String userName; /* userName */

    @Column(name = "user_age", nullable = false)
    private Integer userAge; /* age */

    @Column(name = "user_sex", nullable = false)
    private String userSex; /* sex */

    @Column(name = "created_date", nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP") /* 현재날짜 찍기 */
    private LocalDateTime createdDate;

    public Member(String userId, String userName, Integer userAge, String userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
        this.userSex = userSex;
    }

    public Member() {

    }
}
