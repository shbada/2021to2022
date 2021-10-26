package com.jpa.bookmanager.domain;

import com.jpa.bookmanager.domain.listener.Auditable;
import com.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass /* 해당 클래스의 필드를 상속받는 Entity 에 컬럼으로 포함시켜주겠다는 의미 */
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity implements Auditable {
    //  @Column(name = "crtdat", nullable = false) /** 컬럼 네이밍 지정 (없으면 필드명), null 가능 여부 지정(not null, null) */
    @Column(updatable = false) // update 되지 않음
    @CreatedDate /* AuditingEntityListener */
    private LocalDateTime createdAt;

    // @Column(insertable = false) // insert 되지 않음
    @LastModifiedDate /* AuditingEntityListener */
    private LocalDateTime updatedAt;
}
