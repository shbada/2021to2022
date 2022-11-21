package com.mileage.domain.mileageHistory;

import com.mileage.domain.MileageHistory;

public interface MileageHistoryStore {
    MileageHistory save(MileageHistory mileageHistory);
}
