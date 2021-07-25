package com.elk.api.common.enums;

import lombok.Getter;

public enum EnumOpenApiResponse {
    SUCCESS    ("INFO-000")
    ;

    @Getter
    private final String code;

    EnumOpenApiResponse(String code) {
        this.code = code;
    }
}
