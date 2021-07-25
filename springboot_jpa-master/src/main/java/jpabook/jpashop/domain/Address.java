package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

/* JPA 내장 파일 */
@Embeddable // 임베디드 명시
@Getter // Setter 제거하고 생성자로 데이터 셋팅 (변경 불가능한 클래스로 만들기)
public class Address {
    private String city;
    private String street;
    private String zipcode;

    /** 기본 생성자 생성 (JPA 는 protected 까지 제공)
     *  JPA 는 기본생성자가 무조건 존재해야한다. JPA 구현 라이브러리가 객체를 생성할때 리플렉션 같은 기술을 사용할 수 있도록 지원해야하기 때문 */
    protected Address() {}

    /** Setter 지우고, 아래 생성자로 데이터 셋팅 */
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
