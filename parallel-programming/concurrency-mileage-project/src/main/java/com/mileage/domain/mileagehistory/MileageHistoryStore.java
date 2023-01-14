package com.mileage.domain.mileagehistory;

import com.mileage.domain.MileageHistory;

public interface MileageHistoryStore {
    MileageHistory save(MileageHistory mileageHistory);
}
