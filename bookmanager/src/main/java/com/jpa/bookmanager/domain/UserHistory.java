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

    @Column(name = "user_id")
    private Long userId;
    private String name;
    private String email;

    @CreatedDate /* AuditingEntityListener */
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
