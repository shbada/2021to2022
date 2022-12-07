package com.expedia.b2b.infrastructure;

import com.expedia.b2b.domain.AccommodationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRoomRepository extends JpaRepository<AccommodationRoom, Long> {

}
