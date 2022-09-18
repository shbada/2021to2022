package com.expedia.b2b.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSaveAccommodationDto {
    private AccommodationDto accommodationDto;
    private AccommodationRoomDto accommodationRoomDto;
    private AccommodationRoleDto accommodationRoleDto;
    private AccommodationRoomInfoDto accommodationRoomInfoDto;
    private AccommodationRoomFeeDto accommodationRoomFeeDto;
}
