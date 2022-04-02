package com.spring.batch.itemWriter.jpaWriter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerForm {

    private long id;
    private String firstName;
    private String lastName;
    private String birthdate;
}
