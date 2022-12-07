package com.instagram.api.config.jwt;

import lombok.Getter;

public enum JwtEnum {
    SECRET("westssun"),
    EXPIRATION_TIME("864000000"),
    TOKEN_PREFIX("Bearer "),
    HEADER_STRING("Authorization")
    ;

    @Getter
    private final String value;

    JwtEnum(String value) {
        this.value = value;
    }
}
