package com.api.shop.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Output {
    public <T> ResponseEntity<ResponseDto<T>> send(T body, HttpStatus status, String message) {
        ResponseDto<T> responseDto = ResponseDto.<T>builder()
                .status(status.value())
                .message(message)
                .body(body)
                .build();

        return ResponseEntity.status(status).body(responseDto);
    }

    public ResponseEntity<?> send() {
        return this.send(null, HttpStatus.OK, "");
    }

    public <T> ResponseEntity<ResponseDto<T>> send(T object) {
        return this.send(object, HttpStatus.OK, "");
    }
}
