package com.spring.batch._64_writer_delimited;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private long id;
    private String name;
    private int age;

}
