package com.example.orderservice.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class CommonResponse {
    private static String STATUS = "status";
    private static String MESSAGE = "message";
    private static String RESPONSE = "items";

    public ResponseEntity<?> send(HttpStatus httpStatus, String message, Object body) {
        HashMap<String, Object> items = new HashMap<>();
        items.put(STATUS, httpStatus.value());
        items.put(MESSAGE, message);
        items.put(RESPONSE, body);

        return ResponseEntity.status(httpStatus).body(items);
    }

    public ResponseEntity<?> send() {
        return this.send(HttpStatus.OK, "", null);
    }

    public ResponseEntity<?> send(Object object) {
        return this.send(HttpStatus.OK, "", object);
    }
}
