package com.expedia.b2b.infrastructure;

import com.expedia.b2b.domain.*;
import com.expedia.b2b.domain.accommodation.AccommodationStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationStoreImpl implements AccommodationStore {
    private final AccommodationRepository accommodationRepository;
    private final AccommodationRoleRepository accommodationRoleRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final AccommodationRoomInfoRepository accommodationRoomInfoRepository;
    private final AccommodationRoomFeeRepository accommodationRoomFeeRepository;

    /**
     * 숙박 업체 저장
     * @param accommodation
     */
    public void saveAccommodation(Accommodation accommodation) {
        accommodationRepository.save(accommodation);
    }

    /**
     * 숙박업체 권한 정보 저장
     * @param accommodationRole
     */
    public void saveAccommodationRole(AccommodationRole accommodationRole) {
        accommodationRoleRepository.save(accommodationRole);
    }

    /**
     * 숙박업체 객실 정보 저장
     * @param accommodationRoom
     */
    public void saveAccommodationRoom(AccommodationRoom accommodationRoom) {
        accommodationRoomRepository.save(accommodationRoom);
    }

    /**
     * 숙박업체 객실 부가정보 저장
     * @param accommodationRoomInfo
     */
    public void saveAccommodationRoomInfo(AccommodationRoomInfo accommodationRoomInfo) {
        accommodationRoomInfoRepository.save(accommodationRoomInfo);
    }

    /**
     * 숙박업체 객실 요금정보 저장
     * @param accommodationRoomFee
     */
    public void saveAccommodationRoomFee(AccommodationRoomFee accommodationRoomFee) {
        accommodationRoomFeeRepository.save(accommodationRoomFee);
    }
}
