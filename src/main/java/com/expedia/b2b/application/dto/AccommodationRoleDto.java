package com.expedia.b2b.application.dto;

import com.expedia.b2b.domain.Accommodation;
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
public class AccommodationRoleDto {
    private Long id;
    private Accommodation accommodation;
    private String progStatusCd;
    private String stoppedDts;
    private String stoppedRsn;
    private String lastModifiedBy;
    private LocalDateTime lastModified;
    private String createdBy;
    private LocalDateTime createdAt;
}
