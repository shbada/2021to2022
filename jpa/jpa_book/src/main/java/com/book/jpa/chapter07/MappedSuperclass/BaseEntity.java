package com.book.jpa.chapter07.MappedSuperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @MappedSuperClass
 * @Entity 는 실제로 테이블과 매핑되지만 이것은 단순히 매핑 정보를 상속할 목적으로만 사용된다.
 *
 * [특징]
 * - 테이블과 매핑되지 않고 자식 클래스에 엔티티의 매핑 정보를 상속하기 위해 사용한다.
 * - @MappedSuperclass로 지정한 클래스는 엔티티가 아니므로 em.find()나 JPQL에서 사용할 수 없다.
 * - 이 클래스를 직접 생성해서 사용할 일은 거의 없으므로 추상 클래스로 만드는 것을 권장한다.
 */

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
