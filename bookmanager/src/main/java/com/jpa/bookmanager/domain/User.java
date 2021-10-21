package com.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data /* @NoArgsConstructor 기본 생성자 (인자없는 생성자) 포함 : JPA 에서는 반드시 필요하다. */
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
/** create index, alter table user add constraint : 주의점) 해당 인덱스, 제약사항이 실제로 DB에 적용된 것과 다를 수 있다.
 * JPA Entity 를 활용해서 (ddl) 이렇게 생성하면 적용이 직접 되지만,
 * select/update.delete 에서는, 실제 DB에 인덱스가 적용되어있지 않은데 JPA 에 인덱스 설정이 있다고 해서, 인덱스 관련 쿼리가 동작하진 않음
 * DB 설정을 하고 이렇게 JPA 설정을 하진 않음 (보통)
 * (UniqueConstraint : 복합컬럼, 단일컬럼은 @Column(unique=true) 사용
 * */
@Table(name = "user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
// @Table(name = "user_legacy") /** Entity 이름을 지정 가능 (일반적으로 클래스명으로 자동 설정) */
public class User { // table name : user
    @Id /** Entity 를 식별해주는 PK 를 지정한다 */
    /** strategy
     * 1) TABLE : DB 종류에 상관없이 id 를 관리하는 별도의 테이블을 만들어두고, 이를 추출하여 사용
     * 2) SEQUENCE : Oracle 사용시 (call next value for hibernate_sequence: insert 구문 수행시 시퀀스 증가값을 채움)
     * 3) IDENTITY : Mysql, Maria DB 사용시 (auto increment 값, 트랜잭션 동작 전에 값 설정)
     * 4) AUTO : default. (값 DB에 적합한 값을 자동으로 넘겨줌: DB 의존성 없음)
     */
    @GeneratedValue /** 자동으로 증가하는 값 (직접 쓰겠다는게 아닌, 생성된 값을 사용하겠다는 의미) */
    private Long id;

    @NonNull
    // @Column(unique = true)
    private String name;

    @NonNull
    private String email;

    //  @Column(name = "crtdat", nullable = false) /** 컬럼 네이밍 지정 (없으면 필드명), null 가능 여부 지정(not null, null) */
    @Column(updatable = false) // update 되지 않음
    private LocalDateTime createdAt;

    // @Column(insertable = false) // insert 되지 않음
    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

//    @Transient /* 영속성 데이터에서 제외 (DB 데이터와 관련되지 않음 - ddl 까지도 반영X) */
//    private String testData;

    @Enumerated(value = EnumType.STRING) /** 필수 */
    private Gender gender;
}
