package com.spring.batch._50_reader_flatExceptionHandling;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

    @Override
    public Customer mapFieldSet(FieldSet fs) {

        if(fs == null){
            return null;
        }

        Customer customer = new Customer();
        customer.setName(fs.readString("name"));
        customer.setAge(fs.readInt("age"));
        customer.setYear(fs.readString("year"));

        return customer;
    }
}
