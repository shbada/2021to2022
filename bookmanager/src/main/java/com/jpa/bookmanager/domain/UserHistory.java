package com.jpa.bookmanager.domain;

import com.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = {MyEntityListener.class})
//@EntityListeners(value = {AuditingEntityListener.class})
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    // @ManyToOne 해야하므로 insertable = false, updatable = false 추가
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    private String name;
    private String email;

    @CreatedDate /* AuditingEntityListener */
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // User 의 PK를 USER_HISTORY 가 가지게된다.
    @ManyToOne
    @ToString.Exclude
    private User user;
}
