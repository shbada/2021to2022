package com.seohae.batch.batch.fileBatch1.entity;

import com.seohae.batch.batch.fileBatch2.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer {
    @Id
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleInitial")
    private String middleInitial;

    @Column(name = "lastName")
    private String lastName;

    private String addressNumber;
    private String street;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    private List<Transaction> transactions;

    public Customer() {}

	public Customer(String firstName, String middleName, String lastName, String addressNumber, String street, String city, String state, String zipCode) {
		this.firstName = firstName;
		this.middleInitial = middleName;
		this.lastName = lastName;
		this.addressNumber = addressNumber;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
}
