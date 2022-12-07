package com.sample.api.enums;

import lombok.Getter;

public enum EnumMessage {
    /** HTTP_STATUS message */
    HTTP_BAD_REQUEST            ("잘못된 요청"),
    HTTP_NOT_FOUND              ("존재하지 않는 API"),
    INTERNAL_SERVER_ERROR       ("서버 에러"),
    HTTP_SUCCESS                ("성공"),

    /** validation message */
    VALID_PARAMETER             ("파라미터 validation을 확인해주세요."),
    ;

    @Getter
    private final String message;

    EnumMessage(String message) {
        this.message = message;
    }
}
