package com.spring.batch.itemReader.flatfileFixedLength;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String name;
    private int age;
    private String year;
}
