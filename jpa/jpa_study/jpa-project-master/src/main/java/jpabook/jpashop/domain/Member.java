package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) // default AUTO
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    /* Order List가 필요한데 이걸 넣는건 실시간 애플리케이션에서 회원의 주문내역을 계속해서 조회해야할까? */
    /* ORDERS에 있는걸 사용하면 될 것같다 */
    /* 테스트를 위해 양방향 추가 */
    @OneToMany(mappedBy = "member") // 연관관계의 주인은 MEMBER
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
