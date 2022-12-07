package com.mileage.common.exception;

import com.mileage.common.response.ErrorCode;
import lombok.Getter;

public class LockFailException extends RuntimeException {
    @Getter
    private final ErrorCode errorCode;

    public LockFailException() {
        super(ErrorCode.LOCK_FAIL.getMessage());
        this.errorCode = ErrorCode.LOCK_FAIL;
    }
}