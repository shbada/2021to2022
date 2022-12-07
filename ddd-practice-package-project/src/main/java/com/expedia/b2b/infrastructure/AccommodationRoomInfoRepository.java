package com.expedia.b2b.infrastructure;

import com.expedia.b2b.domain.AccommodationRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRoomInfoRepository extends JpaRepository<AccommodationRoomInfo, Long> {

}
