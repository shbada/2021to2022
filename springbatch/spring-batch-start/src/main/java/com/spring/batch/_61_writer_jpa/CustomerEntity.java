package com.spring.batch._61_writer_jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CustomerEntity {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String birthdate;
}
