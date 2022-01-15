package com.api.plan.entity;

import com.api.plan.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(unique = true)
    private String memberId;

    private String memberName;

    private String email;

    private String phone;

    private String gender;

    private String age;

    private String jobName;
}
