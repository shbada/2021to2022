package com.jpa.bookmanager.domain;

import com.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data /* @NoArgsConstructor 기본 생성자 (인자없는 생성자) 포함 : JPA 에서는 반드시 필요하다. */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@EntityListeners(value = {MyEntityListener.class, UserEntityListener.class})
/** AuditingEntityListener : 자동 Listener (JPA 에서 제공) */
// @EntityListeners(value = {AuditingEntityListener.class, UserEntityListener.class})
@EntityListeners(value = {UserEntityListener.class})
/** create index, alter table user add constraint : 주의점) 해당 인덱스, 제약사항이 실제로 DB에 적용된 것과 다를 수 있다.
 * JPA Entity 를 활용해서 (ddl) 이렇게 생성하면 적용이 직접 되지만,
 * select/update.delete 에서는, 실제 DB에 인덱스가 적용되어있지 않은데 JPA 에 인덱스 설정이 있다고 해서, 인덱스 관련 쿼리가 동작하진 않음
 * DB 설정을 하고 이렇게 JPA 설정을 하진 않음 (보통)
 * (UniqueConstraint : 복합컬럼, 단일컬럼은 @Column(unique=true) 사용
 * */
// @Table(name = "user", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
// @Table(name = "user_legacy") /** Entity 이름을 지정 가능 (일반적으로 클래스명으로 자동 설정) */
public class User extends BaseEntity { // table name : user
    @Id /** Entity 를 식별해주는 PK 를 지정한다 */
    /** strategy
     * 1) TABLE : DB 종류에 상관없이 id 를 관리하는 별도의 테이블을 만들어두고, 이를 추출하여 사용
     * 2) SEQUENCE : Oracle 사용시 (call next value for hibernate_sequence: insert 구문 수행시 시퀀스 증가값을 채움)
     * 3) IDENTITY : Mysql, Maria DB 사용시 (auto increment 값, 트랜잭션 동작 전에 값 설정)
     * 4) AUTO : default. (값 DB에 적합한 값을 자동으로 넘겨줌: DB 의존성 없음)
     */
    // @GeneratedValue /** 자동으로 증가하는 값 (직접 쓰겠다는게 아닌, 생성된 값을 사용하겠다는 의미) */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /** mysql, mariadb 의 기본 전략이 IDENTITY 이고 id 를 AUTO INCREMENT 하겠다는 의미 */
    private Long id;

    @NonNull
    // @Column(unique = true)
    private String name;

    @NonNull
    private String email;

    //  @Column(name = "crtdat", nullable = false) /** 컬럼 네이밍 지정 (없으면 필드명), null 가능 여부 지정(not null, null) */
//    @Column(updatable = false) // update 되지 않음
//    @CreatedDate /* AuditingEntityListener */
//    private LocalDateTime createdAt;

    // @Column(insertable = false) // insert 되지 않음
//    @LastModifiedDate /* AuditingEntityListener */
//    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

//    @Transient /* 영속성 데이터에서 제외 (DB 데이터와 관련되지 않음 - ddl 까지도 반영X) */
//    private String testData;

    @Enumerated(value = EnumType.STRING) /** 필수 */
    private Gender gender;

    /**
     * 1 : N 관계
     */
    @OneToMany(fetch = FetchType.EAGER)
    // JPA persist 전에 null 이므로 Null 에러 발생 방지
    // (JPA에서는 해당 값을 조회할때 값이 존재하지않으면 빈 리스트를 넣어주게되지만,JPA persist 일때 문제가되는것)
    /*
    create table user_user_history_list (
       user_id bigint not null,
        user_history_list_id bigint not null
    ) -> join 컬럼 지정 후 제외. (user_history 테이블에 user_histories_id 컬럼이 생기고,
     */
    // insertable = false, updatable = false : userEntity 에서는 userHistory 를 저장/수정을 막는다.
    @JoinColumn(name = "user_id", insertable = false, updatable = false) // 모호하다. @Column(name = "user_id") 을 userHistory 에 추가해주자.
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    /** entity listener : @EntityListeners(value = MyEntityListener.class) */
//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PrePersist
//    public void prePersist() {
//        System.out.println(">>>>> prePersist");
//    }
//
//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>>>> postPersist");
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        System.out.println(">>>>> preUpdate");
//    }
//
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println(">>>>> postUpdate");
//    }
//
//    @PreRemove
//    public void preRemove() {
//        System.out.println(">>>>> preRemove");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println(">>>>> postRemove");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println(">>>>> postLoad");
//    }

//    @PrePersist // insert 가 호출되기 전에 호출되는 메서드
//    @PreUpdate
//    @PreRemove
//    @PostPersist // insert 가 호출된 이후 호출되는 메서드
//    @PostUpdate
//    @PostRemove
//    @PostLoad
}
