package com.expedia.b2b.application.dto;

import com.expedia.b2b.domain.AccommodationCommonCode;
import com.expedia.b2b.domain.AccommodationRoom;
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
public class AccommodationRoomInfoDto {
    private Long id;
    private int active;
    private String lastModifiedBy;
    private LocalDateTime lastModified;
    private String createdBy;
    private LocalDateTime createdAt;
}
