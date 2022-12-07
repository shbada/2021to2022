package com.expedia.b2b.infrastructure;

import com.expedia.b2b.domain.AccommodationRoomFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRoomFeeRepository extends JpaRepository<AccommodationRoomFee, Long> {

}
