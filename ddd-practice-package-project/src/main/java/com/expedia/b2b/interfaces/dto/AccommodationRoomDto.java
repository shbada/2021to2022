package com.expedia.b2b.interfaces.dto;

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
public class AccommodationRoomDto {
    private Long id;
    private Accommodation accommodation;
    private String roomType;
    private String roomName;
    private int maxEntranceCnt;
    private String lastModifiedBy;
    private LocalDateTime lastModified;
    private String createdBy;
    private LocalDateTime createdAt;
}
