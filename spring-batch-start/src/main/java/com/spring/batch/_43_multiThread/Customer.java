package com.spring.batch._43_multiThread;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Customer {
    // private String name;
    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;
}
