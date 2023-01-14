package com.spring.batch._53_reader_jdbcCursor;

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
