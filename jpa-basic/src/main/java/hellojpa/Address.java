package hellojpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String street;

    @Column(name = "ZIPCODE")
    private String zipcode;

    public Address() {

    }
}
