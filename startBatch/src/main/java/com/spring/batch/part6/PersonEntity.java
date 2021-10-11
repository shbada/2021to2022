package com.spring.batch.part6;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PERSON") /** TODO 10/11 테이블 찾을 수 없다는 에러 발생, 해결중.... */
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
