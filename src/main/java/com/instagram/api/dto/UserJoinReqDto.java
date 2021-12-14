package com.instagram.api.dto;

import com.instagram.api.entity.User;
import lombok.Data;

@Data
public class UserJoinReqDto {
    private String username;
    private String password;
    private String email;
    private String name;

    /**
     * dto to entity
     * @return
     */
    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
