package jpql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS") // ORDER : 예약어
public class Order {

    @Id @GeneratedValue
    private Long id;

    private int orderAmount;

    @Embedded
    private Address address; // 값타입

    @ManyToOne // Many쪽 (ORDER) 에 PRODUCT_ID 컬럼 생성됨
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public void setAddress(Address address) {
        this.address = address;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Address getAddress() {
        return address;
    }

//    public void setAddress(Address address) {
//        this.address = address;
//    }
}
