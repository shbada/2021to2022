package com.project.springbatch._43_multiThread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiThreadCustomer {
    // private String name;
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
