package com.spring.batch._56_reader_jpaPaging;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long Id;
    private String username;
    private int age;

    @OneToOne(mappedBy = "customer")
    private Address address;

}
