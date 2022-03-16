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
    // category_item : 중간 테이블명
    /*
        create table category_item (
           cateogry_id bigint not null,
           item_id bigint not null
        )
     */
    @JoinTable(name = "category_item", joinColumns = @JoinColumn(name = "cateogry_id"),
                inverseJoinColumns =  @JoinColumn(name = "item_id"))
    // 컬렉션 관계를 양쪽에서 해주는 가운데 테이블이 필요하다. (징검다리 역할) - 대신 컬럼 추가가 힘들다. 그래서 실무에서 잘 안쓴다.
    private List<Item> items = new ArrayList<>();


    /* parent */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    /* child - 자식은 여러개를 가질 수 있는 구조 */
    @OneToMany(mappedBy = "parent") // 위 parent (같은 entity 안에 있는)
    private List<Category> child = new ArrayList<>();

    // 연관관계 편의 메서드 (양방향 관계일때 사용하기 좋다. 컨트롤하는 클래스 안에다 넣자.)
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}
