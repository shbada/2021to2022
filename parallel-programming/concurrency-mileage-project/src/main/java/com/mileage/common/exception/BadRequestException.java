package com.mileage.common.exception;

import com.mileage.common.response.ErrorCode;
import lombok.Getter;

public class BadRequestException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}