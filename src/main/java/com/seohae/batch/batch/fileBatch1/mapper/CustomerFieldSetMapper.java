package com.seohae.batch.batch.fileBatch1.mapper;

import com.seohae.batch.batch.fileBatch1.entity.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * flatFileItemReaderStep3 의 .fieldSetMapper(new CustomerFieldSetMapper()) 설정 추가
 */
public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {
    /**
     * 커스터 매퍼 생성 (Customer 타입으로)
     * 원하는 값으로 필드를 매핑할 수 있다.
     * fieldSet 은 타입별로도 가능
     * @param fieldSet
     * @return
     * @throws BindException
     */
    @Override
    public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
        Customer customer = new Customer();
        customer.setAddress(fieldSet.readString("addressName") + " " + fieldSet.readString("street"));
        customer.setCity(fieldSet.readString("city"));
        customer.setFirstName(fieldSet.readString("firstName"));
        customer.setLastName(fieldSet.readString("lastName"));
        customer.setMiddleInitial(fieldSet.readString("middleInitial"));
        customer.setState(fieldSet.readString("state"));
        customer.setZipCode(fieldSet.readString("zipCode"));

        return customer;
    }
}
