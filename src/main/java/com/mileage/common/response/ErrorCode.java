package com.mileage.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public enum ErrorCode {
    HTTP_INTERNAL_SERVER_ERROR  (HttpStatus.INTERNAL_SERVER_ERROR,"9500", "서버에러가 발생하여 요청사항을 수행할 수 없습니다."),
    HTTP_NOT_FOUND              (HttpStatus.NOT_FOUND, "9404", "존재하지 않는 API 입니다."),
    HTTP_UNAUTHORIZED           (HttpStatus.UNAUTHORIZED,"9401", "권한이 없습니다."),
    HTTP_BAD_REQUEST            (HttpStatus.BAD_REQUEST, "9400", "요청이 잘못되었습니다."),

    NOT_EXIST_POINT             (HttpStatus.BAD_REQUEST, "4001", "잘못된 요청입니다."),
    NOT_ENOUGH_POINT            (HttpStatus.BAD_REQUEST, "4002", "포인트가 부족합니다."),
    LOCK_FAIL                   (HttpStatus.INTERNAL_SERVER_ERROR, "5004", "lock 획득에 실패하였습니다.")
    ;

    @Getter
    private final HttpStatus httpStatus;

    @Getter
    private final String code;

    @Getter
    private final String message;

    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
