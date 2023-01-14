package com.project.springbatch._52_reader_json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private long id;
    private String name;
    private int age;
}
