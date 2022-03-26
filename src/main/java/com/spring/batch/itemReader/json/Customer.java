package com.spring.batch.itemReader.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private final long id;
    private final String name;
    private final int age;
}
