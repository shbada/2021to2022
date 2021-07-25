package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany /* 예제 샘플상으로 다대다 관계, 실무에서는 다대다 사용X */
    @JoinTable(name = "category_item", joinColumns = @JoinColumn(name = "cateogry_id"),
                inverseJoinColumns =  @JoinColumn(name = "item_id")) // 컬렉션 관계를 양쪽에서 해주는 가운데 테이블이 필요하다. (징검다리 역할)
    private List<Item> items = new ArrayList<>();


    /* parent */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    /* child */
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    // 연관관계 편의 메서드 (양방향 관계일때 사용하기 좋다. 컨트롤하는 클래스 안에다 넣자.)
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
