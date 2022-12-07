package com.login.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "idx")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(unique = true)
    private String userId;

    private String password;
}
