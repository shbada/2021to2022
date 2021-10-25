package com.jpa.bookmanager.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {
    @PrePersist
    public void prePersist(Object object) { // Object 가 어떤 타입인지 확실히 알 수 없으므로 강제화
        if (object instanceof  Auditable) {
            ((Auditable) object).setCreatedAt(LocalDateTime.now());
            ((Auditable) object).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object object) { // Object 가 어떤 타입인지 확실히 알 수 없으므로 강제화
        if (object instanceof  Auditable) {
            ((Auditable) object).setUpdatedAt(LocalDateTime.now());
        }
    }

}
