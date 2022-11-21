package com.mileage.domain.enums;

import lombok.Getter;

public enum MileageManageType {
    CHARGE       ("적립"),
    USE          ("사용"),
    ;

    @Getter
    private final String type;

    MileageManageType(String type) {
        this.type = type;
    }
}
