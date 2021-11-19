package com.api.westmall.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(unique = true)
    @NonNull
    private String userId;

    @NonNull
    private String password;

    @NonNull
    private String userName;

    /* enum type */
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NonNull
    private String email;

}
