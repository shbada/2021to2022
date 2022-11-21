package com.mileage.infrastructure;

import com.mileage.domain.MileageHistory;
import com.mileage.domain.mileageHistory.MileageHistoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MileageHistoryStoreImpl implements MileageHistoryStore {
    private final MileageHistoryRepository pointHistoryRepository;

    @Override
    public MileageHistory save(MileageHistory mileageHistory) {
        return pointHistoryRepository.save(mileageHistory);
    }
}
