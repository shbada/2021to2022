package com.mileage.common.exception;

import com.mileage.common.response.CommonResponse;
import com.mileage.common.response.ErrorCode;
import com.mileage.common.response.ResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CommonExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseDto<?> error400(BadRequestException e) {
        return CommonResponse.fail(e.getErrorCode());
    }

    @ExceptionHandler(LockFailException.class)
    public ResponseDto<?> lockError500(LockFailException e) {
        log.error(e.getMessage());
        return CommonResponse.fail(e.getErrorCode());
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoHandlerFoundException.class})
    public ResponseDto<?> error404(Exception e) {
        return CommonResponse.fail(ErrorCode.HTTP_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseDto<?> error500(Exception e) {
        e.printStackTrace();
        return CommonResponse.fail(ErrorCode.HTTP_INTERNAL_SERVER_ERROR);
    }
}
