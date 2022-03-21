package com.spring.batch._멀티_스레드_프로세싱.syncrhnizedItemReader;

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
