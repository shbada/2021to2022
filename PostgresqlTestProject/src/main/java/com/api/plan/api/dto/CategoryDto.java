package com.api.plan.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long idx;
    private String categoryName;
}
