package com.api.shop.modules.entity;

import com.api.shop.config.security.Role;
import com.api.shop.modules.form.MemberUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    private Long idx;

    @Column(unique=true) // unique
    private String memberName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded // 내장타입을 명시
    private Address address;

    /**
     * 유저 권한 셋팅
     */
    public void setRoleUser() {
        this.role = Role.ROLE_USER;
    }

    /**
     * 패스워드 업데이트
     * @param password
     */
    public void updatePassword(String password) {
        this.password = password;
    }
}
