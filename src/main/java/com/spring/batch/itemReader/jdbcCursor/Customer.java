package com.spring.batch.itemReader.jdbcCursor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long Id;
    private String firstName;
    private String lastName;
    private String birthdate;

}
