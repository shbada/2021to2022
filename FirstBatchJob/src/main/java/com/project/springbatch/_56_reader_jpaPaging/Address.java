package com.project.springbatch._56_reader_jpaPaging;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long Id;
    private String location;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
