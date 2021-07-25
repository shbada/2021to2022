package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.excpetion.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/* 추상클래스 - 상속관계 매핑 (BOOK, ALBUM, MOVIE) */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) /* 테이블 구조 설정 - 하나의 테이블로 간다 (DTYPE에 구분값) */
@DiscriminatorColumn(name = "dtype") // 구분값 컬럼
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    /*
        결국 얘를 변경할때는 setter 메소드가 아닌 아래 비즈니스 로직 메소드를 태워야한다.
     */
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") /* 예제 샘플상으로 다대다 관계, 실무에서는 다대다 사용X */
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 (응집력- 데이터를 가지고있는 클래스에 비즈니스 로직 메서드를 생성해라) ==//

    /**
     * 재고 수량 증가
     * @param quanitity
     */
    public void addStock(int quanitity) {
        this.stockQuantity += quanitity;
    }

    /**
     * 재고 수량 감소
     * @param quanitity
     */
    public void removeStock(int quanitity) {
        int restStock = this.stockQuantity - quanitity;

        // 재고 수량은 0 보다 커야한다.
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }
}
