package com.instagram.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserLoginReqDto {
    private String username;
    private String password;
}
