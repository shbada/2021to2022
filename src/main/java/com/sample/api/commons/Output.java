package com.sample.api.commons;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class Output {
    private static String status = "status";
    private static String response = "items";

    public ResponseEntity<?> send(Object body) {
        HashMap<String, Object> items = new HashMap<>();
        items.put(status, HttpStatus.OK.value());
        items.put(response, body);

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }
}
