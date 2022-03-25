package com.spring.batch.itemReader.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Customer {
    private final long id;
    private final String name;
    private final int age;
}
