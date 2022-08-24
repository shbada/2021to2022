package com.sample.api.exception;

import com.sample.api.commons.Output;
import com.sample.api.enums.EnumMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    private final Output output;

    /**
     * 400 Error
     * @param e
     * @return
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> error400(BadRequestException e) {
        return output.send(e.getItems(), HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * 404 Error
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, NoHandlerFoundException.class})
    public ResponseEntity<?> error404(Exception e) {
        return output.send(null, HttpStatus.NOT_FOUND, EnumMessage.HTTP_NOT_FOUND.getMessage());
    }

    /**
     * 500 Error
     * @param request
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e) {
        /* error printStackTrace */
        e.printStackTrace();

        /* error log */
        log.error("Error : " + e.toString());
        log.error("RequestUrl : " + request.getRequestURL());

        return output.send(null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * validation error
     * @param exception
     * @return
     */
    @ExceptionHandler({BindException.class})
    public ResponseEntity<?> errorValid(BindException exception) {
        List<ValidationItem> items = new ArrayList<>();

        for (ObjectError error : exception.getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            items.add(new ValidationItem(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return output.send(items, HttpStatus.BAD_REQUEST, EnumMessage.VALID_PARAMETER.getMessage());
    }
}
