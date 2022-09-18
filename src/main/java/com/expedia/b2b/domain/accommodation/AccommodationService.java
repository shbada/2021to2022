package com.expedia.b2b.domain.accommodation;

import com.expedia.b2b.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationStore accommodationStore;

    /**
     * 숙박 업체 저장
     * @param accommodation
     */
    public void saveAccommodation(Accommodation accommodation) {
        accommodationStore.saveAccommodation(accommodation);
    }

    /**
     * 숙박업체 권한 정보 저장
     * @param accommodationRole
     */
    public void saveAccommodationRole(AccommodationRole accommodationRole) {
        accommodationStore.saveAccommodationRole(accommodationRole);
    }

    /**
     * 숙박업체 객실 정보 저장
     * @param accommodationRoom
     */
    public void saveAccommodationRoom(AccommodationRoom accommodationRoom) {
        accommodationStore.saveAccommodationRoom(accommodationRoom);
    }

    /**
     * 숙박업체 객실 부가정보 저장
     * @param accommodationRoomInfo
     */
    public void saveAccommodationRoomInfo(AccommodationRoomInfo accommodationRoomInfo) {
        accommodationStore.saveAccommodationRoomInfo(accommodationRoomInfo);
    }

    /**
     * 숙박업체 객실 요금정보 저장
     * @param accommodationRoomFee
     */
    public void saveAccommodationRoomFee(AccommodationRoomFee accommodationRoomFee) {
        accommodationStore.saveAccommodationRoomFee(accommodationRoomFee);
    }
}
