package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter /** Setter 는 데이터 변경이 일어난다. Setter 을 막 열어두면 데이터를 추적하기 어려우므로, 변경 지점이 명확하도록 비즈니스 메서드를 별도로 제공하자 */
public class Member {

    @Id @GeneratedValue /* 시퀀스 사용 */
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // 내장타입을 명시
    private Address address;

    /**
     * Member와 Order는 1:N 관계
     */
    @OneToMany(mappedBy = "member") /* order 는 Orders 테이블에 있는 member 필드이다. 이것에 의해 매핑되었다는 의미로, 연관관계 주인이 아님 - 읽기전용 */
    // 초기화방법 이렇게 사용 (null 에러 발생 방지, 필드에서 바로 초기화하는 것이 안전)
    // 하이버네이트는 persist() 하는 순간 컬렉션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변경되는데, 컬렉션을 잘못 생성하면 하이버네이트 내부 문제가 발생한다.
    // 그래서 new 해온 객체와 pertsis() 이후 해당 객체를 찍어보면 다른 객체로 나오게된다. 그래서 컬렉션을 필드 자체에서 초기화하고, 컬렉션을 절대 바꾸지마라.
    private List<Order> orders = new ArrayList<>();

}
