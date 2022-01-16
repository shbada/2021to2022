package com.api.plan.api.entity.base;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
    private String regDts;
    private String modDts;
    private String isDeleted;
}
