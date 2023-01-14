package com.sample.api.enums;

import lombok.Getter;

public enum EnumResponse {
    STATUS ("status"),
    MESSAGE ("message"),
    ITEM ("item")
    ;

    @Getter
    private final String value;

    EnumResponse(String value) {
        this.value = value;
    }
}
