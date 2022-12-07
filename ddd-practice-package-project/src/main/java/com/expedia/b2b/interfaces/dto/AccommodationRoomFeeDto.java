package com.expedia.b2b.interfaces.dto;

import com.expedia.b2b.domain.AccommodationRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationRoomFeeDto {
    private Long id;
    private int active;
    private String feeName;
    private BigDecimal fee;
    private String lastModifiedBy;
    private LocalDateTime lastModified;
    private String createdBy;
    private LocalDateTime createdAt;
}
