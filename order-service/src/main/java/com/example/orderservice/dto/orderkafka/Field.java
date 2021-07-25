package com.example.orderservice.dto.orderkafka;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private String type;
    private boolean optional;
    private String field;
}
