package com.expedia.b2b.application.accommodation;

import com.expedia.b2b.domain.*;
import com.expedia.b2b.domain.accommodation.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationFacade {
    private final AccommodationService accommodationService;

    /**
     * 숙박 업체 저장
     * @param accommodation
     */
    public void saveAccommodation(Accommodation accommodation) {
        accommodationService.saveAccommodation(accommodation);
    }

    /**
     * 숙박업체 권한 정보 저장
     * @param accommodationRole
     */
    public void saveAccommodationRole(AccommodationRole accommodationRole) {
        accommodationService.saveAccommodationRole(accommodationRole);
    }

    /**
     * 숙박업체 객실 정보 저장
     * @param accommodationRoom
     */
    public void saveAccommodationRoom(AccommodationRoom accommodationRoom) {
        accommodationService.saveAccommodationRoom(accommodationRoom);
    }

    /**
     * 숙박업체 객실 부가정보 저장
     * @param accommodationRoomInfo
     */
    public void saveAccommodationRoomInfo(AccommodationRoomInfo accommodationRoomInfo) {
        accommodationService.saveAccommodationRoomInfo(accommodationRoomInfo);
    }

    /**
     * 숙박업체 객실 요금정보 저장
     * @param accommodationRoomFee
     */
    public void saveAccommodationRoomFee(AccommodationRoomFee accommodationRoomFee) {
        accommodationService.saveAccommodationRoomFee(accommodationRoomFee);
    }
}
