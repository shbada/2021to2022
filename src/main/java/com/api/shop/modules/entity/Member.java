package com.api.shop.modules.entity;

import com.api.shop.config.security.Role;
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

    private String memberName;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void setRoleUser() {
        this.role = Role.ROLE_USER;
    }
}
