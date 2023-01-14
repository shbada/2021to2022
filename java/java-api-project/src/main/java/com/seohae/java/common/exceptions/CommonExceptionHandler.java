package com.seohae.java.common.exceptions;

import com.seohae.java.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CommonExceptionHandler {
    private final CommonResponse commonResponse;

    /**
     * 400 에러
     * @param e
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> error400(BadRequestException e) {
        return commonResponse.send(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

    /**
     * 500 에러
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> error500(Exception e) {
        e.printStackTrace();
        return commonResponse.send(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
    }
}
