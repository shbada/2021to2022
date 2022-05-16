package com.spring.batch._46_syncrhnizedItemReader;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    // private String name;
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
