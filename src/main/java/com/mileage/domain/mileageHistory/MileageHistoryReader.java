package com.mileage.domain.mileageHistory;

import com.mileage.domain.MileageHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface MileageHistoryReader {
    List<MileageHistory> searchPointHistoryList(LocalDateTime startDate, LocalDateTime endDate, Long mileageIdx);
}
