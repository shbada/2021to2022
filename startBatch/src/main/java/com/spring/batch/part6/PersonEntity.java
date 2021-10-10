package com.spring.batch.part6;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@NoArgsConstructor /* 기본 생성자 */
@Getter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String age;
    private String address;

    public PersonEntity(String name, String age, String address) {
        this(0, name, age, address);
    }

    public PersonEntity(int id, String name, String age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
