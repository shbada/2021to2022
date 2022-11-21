package com.mileage.infrastructure;

import com.mileage.domain.MileageHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface MileageHistoryCustomRepository {
    List<MileageHistory> getPointHistoryList(LocalDateTime startDate, LocalDateTime endDate, Long mileageIdx);
}
