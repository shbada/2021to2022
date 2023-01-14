package hellojpa;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // ITEM 테이블은 안만들어진다. (대신 아래 추상클래스로 변경할것, @DiscriminatorColumn는 의미없음)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일테이블
//@Inheritance(strategy = InheritanceType.JOINED) // 단일 테이블이 아닌 조인 전략으로 변경됨
@DiscriminatorColumn // DType 에 Entity 명이 생성된다. 어떤 자식의 테이블에 의해 들어온건지 구분이 가능하다. (단일테이블은 생략가능)
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
