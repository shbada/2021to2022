package com.api.shop.modules.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

/* JPA 내장 파일 */
@Embeddable // 임베디드 명시
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {} // protected

    /** Setter 지우고, 아래 생성자로 데이터 셋팅 */
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
