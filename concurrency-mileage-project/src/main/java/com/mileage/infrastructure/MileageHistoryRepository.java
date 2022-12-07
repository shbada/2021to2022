package com.mileage.infrastructure;

import com.mileage.domain.MileageHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MileageHistoryRepository extends JpaRepository<MileageHistory, Long>, MileageHistoryCustomRepository {
}
