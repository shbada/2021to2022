package com.api.shop.modules.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long idx;

    private String memberName;

    private String password;
}
