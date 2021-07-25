package com.example.userservice.common.enums;

import lombok.Getter;

public enum EnumUserCntsType {
    BASIC_CNTS    ("10")
    ;

    @Getter
    private final String code;

    EnumUserCntsType(String code) {
        this.code = code;
    }
}
