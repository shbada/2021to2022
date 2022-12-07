package com.instagram.api.common.exception;

import com.instagram.api.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        return commonResponse.send(null, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * @Valid error
     * @param exception
     * @return
     */
    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> errorValid(BindException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder stringBuilder = new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            stringBuilder.append(fieldError.getField()).append(":");
            stringBuilder.append(fieldError.getDefaultMessage());
            stringBuilder.append(", ");
        }

        return commonResponse.send(null, HttpStatus.BAD_REQUEST, stringBuilder.toString());
    }

    /**
     * 500 에러
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> error500(Exception e) {
        e.printStackTrace();
        return commonResponse.send(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
