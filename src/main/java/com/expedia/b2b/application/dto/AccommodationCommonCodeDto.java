package com.expedia.b2b.application.dto;

import com.expedia.b2b.domain.AccommodationGroupCommonCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationCommonCodeDto {
    private Long id;
    private String code;
    private String codeName;
    private String codeDesc;
    private String lastModifiedBy;
    private LocalDateTime lastModified;
    private String createdBy;
    private LocalDateTime createdAt;
}
