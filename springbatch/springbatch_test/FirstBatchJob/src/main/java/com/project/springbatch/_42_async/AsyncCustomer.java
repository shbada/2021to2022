package com.project.springbatch._42_async;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AsyncCustomer {
    // private String name;
    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date birthdate;
}
