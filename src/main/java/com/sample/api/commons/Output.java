package com.sample.api.commons;

import com.sample.api.enums.EnumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class Output {
    public ResponseEntity<HashMap<String, Object>> send(Object body) {
        HashMap<String, Object> items = new HashMap<>();
        items.put(EnumResponse.STATUS.getValue(), HttpStatus.OK.value());
        items.put(EnumResponse.MESSAGE.getValue(), "");
        items.put(EnumResponse.ITEM.getValue(), body);

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    public ResponseEntity<HashMap<String, Object>> send(Object body, HttpStatus status, String message) {
        HashMap<String, Object> items = new HashMap<>();
        items.put(EnumResponse.STATUS.getValue(), status.value());
        items.put(EnumResponse.MESSAGE.getValue(), message);
        items.put(EnumResponse.ITEM.getValue(), body);

        return ResponseEntity.status(status).body(items);
    }

}
